package beans.admin.product;

import entities.Images;
import entities.ProductImages;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import models.ImageModel;
import models.ProductImageModel;
import models.ProductModel;

@ManagedBean
@ViewScoped
public class AdminEditImageProduct {

    @EJB
    private ProductImageModel productImageModel;
    @EJB
    private ImageModel imageModel;

    @EJB
    private ProductModel productModel;

    //bean variables
    private int currentPid;
    private Products product;
    private Part upload_img;
    //view params
    private String pid;

    public AdminEditImageProduct() {
    }

    public void init() {
        currentPid = ApplicationHelper.isInteger(pid) ? Integer.parseInt(pid) : 0;
        product = productModel.getById(currentPid);
        if (product == null) {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
            return;
        }
    }

    public void upload() throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        int fileSize = (int) upload_img.getSize();
        String uploadDir = ApplicationHelper.getExternalContext().getInitParameter("uploadDirectory");
        String uploadImgDir = uploadDir + "/images";
        File fileUploadDir = new File(uploadImgDir);

        if (!fileUploadDir.exists()) {
            fileUploadDir.mkdirs();
        }
        String fileName = getFilename(upload_img);
        File outputFile = new File(uploadImgDir + "/" + fileName);
        String extension = getFileExtension(outputFile);
        String newFileName = ApplicationHelper.secureRandomString(8) + ApplicationHelper.secureRandomString(8) + "." + extension;
        outputFile = new File(uploadImgDir + "/" + newFileName);

        try {

            inputStream = upload_img.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[fileSize];
            int bytesRead = 0;
            while (true) {
                bytesRead = inputStream.read(buffer);
                if (bytesRead > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                } else {
                    break;
                }
            }
        } catch (IOException ex) {

        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        if (outputFile.exists()) {
            Images image = new Images();
            image.setImgPath(newFileName);
            image.setImgSize(fileSize);
            image.setCreateAt(PersistenceHelper.getCurrentTime());

            boolean result = imageModel.create(image);
            if (result) {
                ProductImages pi = new ProductImages();
                pi.setImgId(image);
                pi.setProductId(product);
                boolean insertPiResult = productImageModel.create(pi);
                if (insertPiResult) {
                    ApplicationHelper.addMessage("Image uploaded!");
                } else {
                    ApplicationHelper.addMessage("Failed to save image data!");
                }
            } else {
                outputFile.delete();
            }
        }
        ApplicationHelper.redirect("/admin/product/edit_image.xhtml?pid=" + product.getPid(), true);
        return;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public int getCurrentPid() {
        return currentPid;
    }

    public void setCurrentPid(int currentPid) {
        this.currentPid = currentPid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Part getUpload_img() {
        return upload_img;
    }

    public void setUpload_img(Part upload_img) {
        this.upload_img = upload_img;
    }
}

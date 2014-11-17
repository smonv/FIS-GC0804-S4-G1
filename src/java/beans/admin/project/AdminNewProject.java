package beans.admin.project;

import beans.admin.product.AdminEditImageProduct;
import entities.Admins;
import entities.Contracts;
import entities.Images;
import entities.ListStatus;
import entities.Projects;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import models.ContractModel;
import models.ImageModel;
import models.ListStatusModel;
import models.ProjectModel;

@ManagedBean
@ViewScoped
public class AdminNewProject {

    @EJB
    private ImageModel imageModel;

    @EJB
    private ListStatusModel listStatusModel;

    @EJB
    private ProjectModel projectModel;
    @EJB
    private ContractModel contractModel;

    //bean variable
    private Contracts contract;
    private int currentCid;
    private String title;
    private String content;
    private String startAt;
    private String endAt;
    private int projectStatus;
    private Part uploadImg;
    private boolean isPublic;
    //view param
    private String cid;

    public AdminNewProject() {
    }

    public void init() {
        currentCid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0;
        contract = contractModel.getById(currentCid);
        if (contract == null) {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
        }
    }

    public List<ListStatus> getListStatus() {
        List<ListStatus> status = listStatusModel.getAll();
        return status;
    }

    public void create() throws IOException {
        Images uploadImg = upload();
        if (uploadImg != null) {
            boolean valid = true;
            Date startAtDate = ApplicationHelper.parseDate(startAt, "dd/MM/yyyy");
            Date endAtDate = ApplicationHelper.parseDate(endAt, "dd/MM/yyyy");
            Date currentDate = PersistenceHelper.getCurrentTime();
            if (startAtDate.compareTo(currentDate) < 0) {
                ApplicationHelper.addMessage("Start date must be after current time!");
                valid = false;
            }
            if (endAtDate.compareTo(currentDate) < 0) {
                ApplicationHelper.addMessage("End date must be after current time!");
                valid = false;
            }
            if (startAtDate.compareTo(endAtDate) > 0) {
                ApplicationHelper.addMessage("Start date must be before end date!");
                valid = false;
            }
            if (!valid) {
                ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + cid, true);
            }
            //////////////
            currentCid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0;
            contract = contractModel.getById(currentCid);
            if (contract == null) {
                ApplicationHelper.addMessage("Can't find exists contract!");
                ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + cid, valid);
            }
            /////////

            Projects project = new Projects();
            project.setAdminId(new Admins(1)); //will change when finish admin filter
            project.setImgId(uploadImg);
            project.setContractId(contract);
            project.setTitle(title);
            project.setContent(content);
            project.setProjectStatus(new ListStatus(projectStatus));
            project.setIsPublic(isPublic);
            project.setStartAt(startAtDate);
            project.setEndAt(endAtDate);
            project.setCreateAt(PersistenceHelper.getCurrentTime());

            boolean result = projectModel.create(project);
            if (result) {
                ApplicationHelper.addMessage("Project created!");
                ApplicationHelper.redirect("/admin/contract/view.xhtml?cid=" + contract.getCid(), true);

            } else {
                ApplicationHelper.addMessage("Failed to create new project!");
                ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + contract.getCid(), true);

            }
        } else {
            ApplicationHelper.addMessage("Failed to save upload image!");
            ApplicationHelper.redirect("/admin/project/new.xhtml?cid=" + contract.getCid(), true);
        }
    }

    public Images upload() throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        int uploadFileSize = (int) uploadImg.getSize();
        String uploadDir = ApplicationHelper.getExternalContext().getInitParameter("uploadDirectory");
        String uploadImgDir = uploadDir + "/images";
        File fileUploadDir = new File(uploadImgDir);

        if (!fileUploadDir.exists()) {
            fileUploadDir.mkdirs();
        }
        String fileName = getFilename(uploadImg);
        File outputFile = new File(uploadImgDir + "/" + fileName);
        String extension = getFileExtension(outputFile);
        String newFileName = ApplicationHelper.secureRandomString(8) + ApplicationHelper.secureRandomString(8) + "." + extension;
        outputFile = new File(uploadImgDir + "/" + newFileName);

        try {

            inputStream = uploadImg.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[uploadFileSize];
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
            ex.printStackTrace();
            return null;

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
            image.setImgSize(uploadFileSize);
            image.setCreateAt(PersistenceHelper.getCurrentTime());

            image = imageModel.createAndReturnImage(image);
            return image;
        }
        return null;
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /// SET GET
    public Contracts getContract() {
        return contract;
    }

    public void setContract(Contracts contract) {
        this.contract = contract;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Part getUploadImg() {
        return uploadImg;
    }

    public void setUploadImg(Part uploadImg) {
        this.uploadImg = uploadImg;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}

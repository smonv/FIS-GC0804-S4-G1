package beans.admin.project;

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
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import models.ImageModel;
import models.ListStatusModel;
import models.ProjectModel;

@ManagedBean
@ViewScoped
public class AdminEditProject {

    @EJB
    private ImageModel imageModel;

    @EJB
    private ListStatusModel listStatusModel;

    @EJB
    private ProjectModel projectModel;

    //bean variables
    private Projects project;
    private int currentPid;
    private String title;
    private String content;
    private boolean isPublic;
    private String startAt;
    private String endAt;
    private int statusId;
    private Part image;
    private Date startDate;
    private Date endDate;
    //view params
    public String pid;

    public AdminEditProject() {
    }

    public void init() {
        currentPid = ApplicationHelper.isInteger(pid) ? Integer.parseInt(pid) : 0;
        project = projectModel.getById(currentPid);

        if (!FacesContext.getCurrentInstance().isPostback() && project != null) {
            title = project.getTitle();
            content = project.getContent();
            isPublic = project.getIsPublic();
            startAt = ApplicationHelper.formatDate(project.getStartAt(), "dd-MM-yyyy");
            endAt = ApplicationHelper.formatDate(project.getEndAt(), "dd-MM-yyyy");
            statusId = project.getProjectStatus().getLsid();
        }
    }

    public void update() throws IOException {
        Images uploadImage = image != null ? upload() : null;
        boolean valid = true;
        startDate = ApplicationHelper.parseDate(startAt, "dd-MM-yyyy");
        endDate = ApplicationHelper.parseDate(endAt, "dd-MM-yyyy");
        Date currentDate = PersistenceHelper.getCurrentTime();

        if (startDate.compareTo(endDate) > 0) {
            ApplicationHelper.addMessage("Start date must be before end date!");
            valid = false;
        }
        if (!valid) {
            ApplicationHelper.navigate("/admin/project/edit.xhtml?pid=" + pid);
        } else {
            currentPid = ApplicationHelper.isInteger(pid) ? Integer.parseInt(pid) : 0;
            project = projectModel.getById(currentPid);
            project.setTitle(title);
            project.setContent(content);
            project.setIsPublic(isPublic);
            project.setProjectStatus(new ListStatus(statusId));
            if (uploadImage != null) {
                project.setImgId(uploadImage);
            }
            project.setStartAt(startDate);
            project.setEndAt(endDate);
            project.setUpdateAt(PersistenceHelper.getCurrentTime());
            boolean result = projectModel.update(project);
            if (result) {
                ApplicationHelper.addMessage("Project updated!");
                ApplicationHelper.redirect("/admin/project/view.xhtml?pid=" + project.getPid(), result);
            } else {
                ApplicationHelper.addMessage("Failed to update project!");
                ApplicationHelper.navigate("/admin/project/edit.xhtml?pid=" + project.getPid());
            }
        }
    }

    public Images upload() throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        int uploadFileSize = (int) image.getSize();
        String uploadDir = ApplicationHelper.getExternalContext().getInitParameter("uploadDirectory");
        String uploadImgDir = uploadDir + "/images";
        File fileUploadDir = new File(uploadImgDir);

        if (!fileUploadDir.exists()) {
            fileUploadDir.mkdirs();
        }
        String fileName = getFilename(image);
        File outputFile = new File(uploadImgDir + "/" + fileName);
        String extension = getFileExtension(outputFile);
        String newFileName = ApplicationHelper.secureRandomString(8) + ApplicationHelper.secureRandomString(8) + "." + extension;
        outputFile = new File(uploadImgDir + "/" + newFileName);

        try {

            inputStream = image.getInputStream();
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

    public List<ListStatus> getAllStatus() {
        return listStatusModel.getAll();
    }

    ////SET GET
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

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
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

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

}

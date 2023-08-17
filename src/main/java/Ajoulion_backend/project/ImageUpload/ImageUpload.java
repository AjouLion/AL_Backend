package Ajoulion_backend.project.ImageUpload;

import Ajoulion_backend.project.Config.WebMvcConfig;
import Ajoulion_backend.project.Table.Entity.Device;
import Ajoulion_backend.project.Table.Entity.Users;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class ImageUpload {

    private static String imageDir = null;
    private static final String profileImageDir = "profile/";
    private static final String certificationImageDir = "certification/";
    private static final String deviceImageDir = "device/";

    @Value("${file.upload.directory}")
    public void SetImageDir(String value) {
        imageDir = value;
        log.info("SetImageDir.imageDir="+imageDir);
    }

    public static String getImageDir() {
        log.info("getImageDir.imageDir="+imageDir);
        return imageDir;
    }

    public static String joinPath(String path1, String path2) {
        if (path2 == null || path2.isEmpty()) return path1;
        if (path1.charAt(path1.length() - 1) == '/') return path1 + path2;
        return path1 + "/" + path2;
    }

    public static String getDir(String subDir) {
        return joinPath(imageDir, subDir);
    }

    public static String getName(String path) {
        File file = new File(path.replace("\\", "/"));
        return file.getName();
    }

    public static String uploadFile(String subDir, MultipartFile uploadFile) {
        if (uploadFile == null || uploadFile.isEmpty()) return null;
        String dir = getDir(subDir);
        File file = new File(dir);
        if (!file.exists()) file.mkdirs();
        String filename = getName(uploadFile.getOriginalFilename());
        log.info("dir=[" + dir + "] filename=[" + filename+"]");
        file = new File(joinPath(dir, filename));
        try {
            uploadFile.transferTo(file);
        } catch (IOException e) {
            return null;
        }
        return filename;
    }

    public static void deleteFile(String path) {
        if (path == null) return;
        log.info("delete file = " + path);
        File file = new File(path);
        try {
            file.delete();
        } catch(Exception e) {
            log.info("delete error = " + path);
        }
    }

    public static String getProfileDir(Long userId) {
        return joinPath(profileImageDir, userId.toString());
    }

    public static String getCertificationDir(Long userId) {
        return joinPath(certificationImageDir, userId.toString());
    }

    public static String getDeviceDir(Long deviceId) {
        return joinPath(deviceImageDir, deviceId.toString());
    }

    public static String getProfilePath(Long userId, String filename) {
        if (filename == null) return null;
        return joinPath(getProfileDir(userId), filename);
    }

    public static String getCertificationPath(Long userId, String filename) {
        if (filename == null) return null;
        return joinPath(getCertificationDir(userId), filename);
    }

    public static String getDevicePath(Long deviceId, String filename) {
        if (filename == null) return null;
        return joinPath(getDeviceDir(deviceId), filename);
    }

    public static String uploadProfileImage(Long userId, MultipartFile imageFile) {
        return uploadFile(getProfileDir(userId), imageFile);
    }

    public static String uploadCertificationImage(Long userId, MultipartFile imageFile) {
        return uploadFile(getCertificationDir(userId), imageFile);
    }

    public static String uploadDeviceImage(Long deviceId, MultipartFile imageFile) {
        return uploadFile(getDeviceDir(deviceId), imageFile);
    }

    public static void deleteProfileImage(Users user) {
        deleteFile(getProfilePath(user.getUserId(), user.getProfile()));
    }

    public static void deleteCertificationImage(Users user) {
        deleteFile(getCertificationPath(user.getUserId(), user.getCertification()));
    }

    public static void deleteDeviceImage(Device device) {
        deleteFile(getDevicePath(device.getDeviceId(), device.getImage()));
    }
}

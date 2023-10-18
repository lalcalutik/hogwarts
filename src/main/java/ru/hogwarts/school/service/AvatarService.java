package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }


    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked uploadAvatar method");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> {
            logger.error(String.format("Student with id = %d not found!", studentId));
            return new StudentNotFoundException(studentId);
        });
        Path filePath = Path.of(avatarsDir, student + "." + StringUtils.getFilenameExtension(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = getAvatarByStudentID(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar getAvatarByStudentID(Long studentId) {
        logger.info("Was invoked getAvatarByStudentID method");
        return avatarRepository.findByStudent_Id(studentId).orElse(new Avatar());
    }

    public Collection<String> getAllAvatars(Integer page, Integer pageSize) {
        logger.info("Was invoked getAllAvatars method");
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent().stream()
                .map(Avatar::getFilePath)
                .toList();
    }
}

package com.globits.demo.service.implement;

import com.globits.demo.dto.PersonViewDTO;
import com.globits.demo.mapper.PersonMapper;
import com.globits.demo.model.Person;
import com.globits.demo.repository.PersonRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PersonAvatar {


    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    // where to store images, e.g. project root /uploads/avatars
    private final Path avatarRootFolder = Paths.get(System.getProperty("user.dir"), "upload", "avatar");

    public PersonAvatar(PersonRepository personRepository,
                               PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Transactional
    public PersonViewDTO saveAvatar(int personId, MultipartFile file) {

        Person person = personRepository.get(personId);
        if (person == null) return null;
        System.out.println("Received file: " + file.getOriginalFilename() + ", size: " + file.getSize());

        String imgName = file.getOriginalFilename();
        if (imgName == null || imgName.isBlank()) return null;
        String fileType = imgName.substring(imgName.lastIndexOf('.') + 1).toLowerCase();

        boolean isImage = fileType.equals("jpg") || fileType.equals("jpeg") || fileType.equals("png");
        if (!isImage) return null;
        System.out.println("File type: " + fileType);

        try {
            // create folder if not exists
            Files.createDirectories(avatarRootFolder);

            // new file name: {personId}.{ext}
            String newFileName = personId + "." + fileType;
            System.out.println("Saving file as: " + newFileName);

            Path target = avatarRootFolder.resolve(newFileName);
            System.out.println("Target path: " + target.toAbsolutePath());

            // save file to disk
            file.transferTo(target.toFile());

            // save relative path to DB
            person.setAvatar(target.toString());
            Person saved = personRepository.save(person);

            return personMapper.toViewDto(saved);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}

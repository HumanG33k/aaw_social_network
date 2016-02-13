/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.FilesEntity;
import dao.UsersEntity;
import java.util.ArrayList;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jerome
 */
public interface FilesService {

    public void add(MultipartFile file, UsersEntity usersEntity);
    public ArrayList<FilesEntity> searchByOwner(UsersEntity owner);
    
}

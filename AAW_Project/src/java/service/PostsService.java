/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Nathanael Villemin
 */
public interface PostsService {
    public void add(String content, Long userId, Long wallId);
}

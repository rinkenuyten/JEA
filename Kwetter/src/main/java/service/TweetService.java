/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TweetDAO;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rinke
 */
@Stateless
public class TweetService {
    
    @Inject
    TweetDAO td;
}

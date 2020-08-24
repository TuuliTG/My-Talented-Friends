/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.Controllers;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Skill;
import projekti.domain.User;
import projekti.repositories.SkillRepository;
import projekti.repositories.UserRepository;
import projekti.service.UserService;

/**
 *
 * @author tgtuuli
 */
@Controller
public class skillController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private SkillRepository skillRepository;
    
    
    @GetMapping("addskills")
    public String skillForm(){
        return "addskills";
    }
    @PostMapping("skills")
    public String addASkill(@RequestParam String skill){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        this.userService.addASkill(skill, username);
        return "redirect:/addskills";
        
    }
    
    @PostMapping("/{username}/praise/{skillid}")
    public String praise(@PathVariable String skillid, @PathVariable String username){
        
        Long skillId = Long.parseLong(skillid);
        Skill skill = this.skillRepository.getOne(skillId);
        skill.setLikes(skill.getLikes()+1);
        this.skillRepository.save(skill);
        return "redirect:/userHomePage/" + username;
    }
}

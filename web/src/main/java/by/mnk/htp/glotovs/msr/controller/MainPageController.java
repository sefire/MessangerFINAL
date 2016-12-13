package by.mnk.htp.glotovs.msr.controller;

import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.entities.FriendEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.impl.ChatService;
import by.mnk.htp.glotovs.msr.services.impl.FriendService;
import by.mnk.htp.glotovs.msr.services.impl.UserService;
import by.mnk.htp.glotovs.msr.services.interfaces.IChatService;
import by.mnk.htp.glotovs.msr.services.interfaces.IFriendService;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import by.mnk.htp.glotovs.msr.vo.UserPaginationVO;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Glotov on 11.12.2016.
 */

@Controller
public class MainPageController {

    @Autowired
    private IFriendService friendService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IChatService chatService;

    private UserEntity currentUserEntity;

    @RequestMapping(value = {"/main"})
    public String passRegistration(HttpSession httpSession) {
        //ModelAndView modelAndView = new ModelAndView("sessionCurrentUserPhoneNumber");
        httpSession.setAttribute("currentUserPhoneNumber", getPrincipal());

        try {
            currentUserEntity = userService.getUserEntityByPhone(getPrincipal());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping(value = {"/GETMYFRIENDS"})
    public ModelAndView getMyFriends() {
        ArrayList<UserEntity> userFriendsList = null;

        try {
            userFriendsList = (ArrayList<UserEntity>) friendService.getAllFriendsByUserId(currentUserEntity.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ModelAndView("userFriends", "userFriendsList", userFriendsList);
    }

    @RequestMapping(value = {"/GETMYCHATS"})
    public ModelAndView getMyChats() {

        ArrayList<ChatEntity> userChatsList = null;
        try {
            userChatsList = (ArrayList<ChatEntity>) chatService.getUserChatsByUserId(currentUserEntity.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userChats");
        modelAndView.addObject("userChatsList", userChatsList);
        modelAndView.addObject("idCurrentUser", currentUserEntity.getId());
        return modelAndView;
    }

    @RequestMapping(value = {"/GETUSERSINFO"})
    public ModelAndView getUsersIngo() {

        UserEntity currentUserEntityCheck = null;

        try {
            currentUserEntityCheck = (UserEntity) userService.get(currentUserEntity.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ModelAndView("userInfo", "userEntity", currentUserEntityCheck);
    }

    @RequestMapping(value = {"/GETALLUSERS"})
    public ModelAndView getAllUsers() {

        ArrayList<UserEntity> allUsersList = null;
        try {
            allUsersList = (ArrayList<UserEntity>) userService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ModelAndView("allUsers", "alluserslist", allUsersList);
    }

    private ModelAndView paginationUtil( String page, String countPerPage) {
        UserPaginationVO userPaginationVO = null;

        if (countPerPage == null)
            countPerPage = "4";
        if (page == null)
            page = "1";

        try {
            userPaginationVO = userService.paginationUsers(page, Integer.valueOf(countPerPage));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("totaluserscount", userPaginationVO.getTotalUsersCount());
        modelAndView.addObject("countofpages", (int) Math.ceil(userPaginationVO.getTotalUsersCount() * 1.0 / Integer.valueOf(countPerPage)));
        modelAndView.addObject("page", userPaginationVO.getPage());
        modelAndView.addObject("alluserslist", userPaginationVO.getUserEntityList());
        modelAndView.addObject("countPerPage",countPerPage);

        //session.setAttribute("page", userPaginationVO.getPage());

        //session.setAttribute("countPerPage", countPerPage);

        modelAndView.setViewName("userPagination");
        return modelAndView;
    }

    @RequestMapping(value = "/USERSPAGINATION", method = RequestMethod.GET)
    public ModelAndView getAllUsersPaginationFirst() {
        return paginationUtil("1", "5");
    }

    @RequestMapping(value = {"/USERSPAGINATION/{page}/{countPerPage}"}, method = RequestMethod.GET)
    public ModelAndView getAllUsersPagination(@PathVariable String page, @PathVariable String countPerPage) {
        return paginationUtil(page, countPerPage);
    }

    @RequestMapping(value = {"/PRESEACH"})
    public String preSearchUser() {
        return "findUser";
    }

    @RequestMapping(value = {"/findUser"})
    public ModelAndView findUserByPhone(@RequestParam("phoneNumberToFind") String phoneNumberToFind, RedirectAttributes redirectAttributes) {
        UserEntity userEntityFound = null;
        UserEntity userEntityCur = null;
        ModelAndView modelAndView = new ModelAndView();

        try {
            userEntityCur = userService.getUserEntityByPhone(getPrincipal());
            userEntityFound = userService.getUserEntityByPhone(String.valueOf(phoneNumberToFind));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (userEntityFound != null) {
            //redirect:/
            redirectAttributes.addFlashAttribute("anyUser", userEntityFound);
            modelAndView.setViewName("redirect:/smbspage");
            //modelAndView.addObject("anyUser", userEntityFound);
            for (FriendEntity friendEntity : userEntityCur.getFriendEntities())
                if (friendEntity.getUserFriendId() == userEntityFound.getId())
                    redirectAttributes.addFlashAttribute("isItFriend", true);
                    //modelAndView.addObject("isItFriend", true);
        } else {
            modelAndView.setViewName("findUser");
            modelAndView.addObject("userNotFoundMessage", "userNotFoundMessage");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/smbspage"})
    public ModelAndView showSmbsPage(@ModelAttribute("anyUser") UserEntity userEntity, @ModelAttribute("isItFriend") String isItFriend) {
        ModelAndView modelAndView = new ModelAndView("smbspage", "anyUser", userEntity);
        modelAndView.addObject("isItFriend", isItFriend);
        return modelAndView;
    }


    private String getPrincipal() {
        String phoneNumber;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            phoneNumber = ((UserDetails) principal).getUsername();
        } else {
            phoneNumber = principal.toString();
        }
        return phoneNumber;
    }
}



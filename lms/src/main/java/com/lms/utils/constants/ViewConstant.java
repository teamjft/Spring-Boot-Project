package com.lms.utils.constants;

/**
 * Created by bhushan on 16/5/17.
 */
public class ViewConstant {
    /*Home Controller*/
    public static final String INDEX_VIEW = "index";
    public static final String _404_VIEW = "404";
    public static final String _500_VIEW = "500";
    public static final String _403_VIEW = "404";
    public static final String ERROR_VIEW = "error";

    /*User Controller*/
    public static final String USER_PLAN_VIEW = "user/userplan";
    public static final String REDIRECT_HOME_VIEW = "redirect:/";
    public static final String RESET_PASSWORD_VIEW = "user/resetpassword";
    public static final String USER_HOME_VIEW = "user/home";
    public static final String REDIRECT_USER_HOME = "redirect:/user/home";
    /*Book controller*/
    public static final String BOOK_INDEX_VIEW = "book/index";
    public static final String BOOK_VIEW = "book/view";
    public static final String BOOK_CREATE_VIEW = "book/create";
    public static final String REDIRECT_BOOK_INDEX = "redirect:/book/index";
    /*Category Controller*/
    public static final String REDIRECT_CATEGORY_INDEX = "redirect:/category/index";
    public static final String CATEGORY_EDIT_VIEW = "category/edit";
    public static final String CATEGORY_CREATE_VIEW = "category/create";
    public static final String CATEGORY_INDEX_VIEW = "category/index";
    /*Configuration Controller*/
    public static final String CONFIGURATION_REDIRECT_IMAGECONFIGURATION = "redirect:/configuration/imageconfiguration";
    public static final String CONFIGURATION_REDIRECT_TEMPLATE = "redirect:/configuration/template";
    public static final String CONFIGURATION__IMAGECONFIGURATION_VIEW = "configuration/imageconfiguration";
    public static final String CONFIGURATION_TEMPLATE_LIST_VIEW = "configuration/templatelist";
    public static final String CONFIGURATION_EDIT_TEMPLATE_VIEW = "configuration/edittemplate";
    public static final String CONFIGURATION_TEMPLATE_VIEW = "configuration/templateview";
    /*Invitation Controller*/
    public static final String INVITATION_ACCEPT_VIEW  = "invitation/accept";
    public static final String INVITATION_CREATE_VIEW  = "invitation/create";
    public static final String INVITATION_CREATE_INVITE_VIEW  = "invitation/createinvite";
    /*Membership Controller*/
    public static final String MEMBERSHIP_INDEX_VIEW  = "membership/index";
    public static final String MEMBERSHIP_VIEW  = "membership/view";
    public static final String REDIRECT_MEMBERSHIP_INDEX = "redirect:/membership/index";
    /*MembershipPlan Controller*/
    public static final String MEMBERSHIP_PLAN_INDEX_VIEW = "plan/index";
    public static final String MEMBERSHIP_PLAN_CREATE_VIEW = "plan/create";
    public static final String MEMBERSHIP_PLAN_VIEW = "plan/view";
    public static final String MEMBERSHIP_PLAN_PURCHASE_VIEW = "plan/purchase";
    public static final String REDIRECT_MEMBERSHIP_PLAN_INDEX = "forward:/plan/index";
    /*Payment Controller*/
    public static final String PAYMENT_CREATE_VIEW  = "payment/create";
    public static final String PAYMENT_SUCCESS_VIEW  = "payment/success";
    /*Issue Book Controller*/
    public static final String ISSUE_BOOK_CREATE_VIEW  = "issuebook/create";
    public static final String ISSUE_BOOK_ASSIGN_VIEW  = "issuebook/assign";

}

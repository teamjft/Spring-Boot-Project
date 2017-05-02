package com.lms.utils.helper;

import com.lms.models.MemberShip;

/**
 * Created by bhushan on 2/5/17.
 */
public class LibraryUtil {
    public static boolean isLibraryAdminOrLibrarian(MemberShip memberShip) {
        return memberShip != null && (memberShip.isLibrarian() || memberShip.isLibrarian());
    }
}

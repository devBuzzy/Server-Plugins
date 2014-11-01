package me.zerocraft.staffchat;

import me.zerocraft.utils.LogUtils;

import javax.naming.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Backend staff chat tools and such
 */
public enum SCType {

    /**
     * Admin staff chat
     */
    ADMIN,
    /**
     * Moderator staff chat
     */
    MOD,
    /**
     * General staff chat
     */
    STAFF,
    /**
     * Backend notifications staff chat
     */
    NOTIFICATION,
    /**
     * Global "staff chat" for mods and up. Essentially a notifications system like /say, but doesn't requre a command to be run every message to broadcast.
     */
    ALERT,
    /**
     * VIP channel
     */
    VIP,
    /**
     * Build My Thing internal channel
     */
    BMT;

    static List<UUID> admin = new ArrayList<>();
    static List<UUID> mod = new ArrayList<>();
    static List<UUID> bmt = new ArrayList<>();
    static List<UUID> vip = new ArrayList<>();
    static List<UUID> staff = new ArrayList<>();
    static List<UUID> alert = new ArrayList<>();

    static List<UUID> noStaffchat = new ArrayList<>();

    public static void setCanSeeStaffchat(UUID p, boolean canSee) {
        if(canSee) {
            noStaffchat.add(p);
        } else {
            noStaffchat.remove(p);
        }
    }

    public static boolean canSeeStaffchat(UUID p) {
        return !noStaffchat.contains(p);
    }

    public static boolean hasStaffchat(UUID p, SCType type) {
        if(type == ADMIN && admin.contains(p)) {
            return true;
        } else if(type == MOD && mod.contains(p)) {
            return true;
        } else if(type == STAFF && staff.contains(p)) {
            return true;
        } else if(type == ALERT && alert.contains(p)) {
            return true;
        } else if(type == VIP && vip.contains(p)) {
            return true;
        } else if(type == BMT && bmt.contains(p)) {
            return true;
        }

        else {
            return false;
        }
    }

    public static SCType getType(UUID p) {
        if (admin.contains(p)) return ADMIN;
        else if(mod.contains(p)) return MOD;
        else if(staff.contains(p)) return STAFF;
        else if(alert.contains(p)) return ALERT;
        else if(bmt.contains(p)) return BMT;
        else if(vip.contains(p)) return VIP;
        else return null;
    }

    public static void addUser(UUID p, SCType type) throws OperationNotSupportedException {
        if(type == NOTIFICATION) {
            throw new OperationNotSupportedException("You may not set a chat to notifications. The notification chat type is only meant for backend features.");
        }
        if(type == ADMIN) {
            try {
                if(mod.contains(p)) mod.remove(p);
                if(alert.contains(p)) alert.remove(p);
                if(staff.contains(p)) staff.remove(p);
                if(bmt.contains(p)) bmt.remove(p);
                if(vip.contains(p)) vip.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            } finally {
                admin.add(p);
            }
        } else if(type == MOD) {
            try {
                if(admin.contains(p)) admin.remove(p);
                if(alert.contains(p)) alert.remove(p);
                if(staff.contains(p)) staff.remove(p);
                if(bmt.contains(p)) bmt.remove(p);
                if(vip.contains(p)) vip.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            } finally {
                mod.add(p);
            }
        } else if(type == STAFF) {
            try {
                if(mod.contains(p)) mod.remove(p);
                if(admin.contains(p)) admin.remove(p);
                if(alert.contains(p)) alert.remove(p);
                if(bmt.contains(p)) bmt.remove(p);
                if(vip.contains(p)) vip.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            } finally {
                staff.add(p);
            }
        } else if(type == ALERT) {
            try {
                if(mod.contains(p)) mod.remove(p);
                if(admin.contains(p)) admin.remove(p);
                if(staff.contains(p)) staff.remove(p);
                if(bmt.contains(p)) bmt.remove(p);
                if(vip.contains(p)) vip.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            } finally {
                alert.add(p);
            }
        } else if(type == VIP) {
            try {
                if(mod.contains(p)) mod.remove(p);
                if(admin.contains(p)) admin.remove(p);
                if(staff.contains(p)) staff.remove(p);
                if(bmt.contains(p)) bmt.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            } finally {
                vip.add(p);
            }
        } else if(type == BMT) {
            try {
                if(mod.contains(p)) mod.remove(p);
                if(admin.contains(p)) admin.remove(p);
                if(staff.contains(p)) staff.remove(p);
                if(vip.contains(p)) vip.remove(p);
                if(alert.contains(p)) alert.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            } finally {
                bmt.add(p);
            }
        }
    }

    public static void removeUser(UUID p, SCType type) {
        if(type == ADMIN) {
            try {
                admin.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            }
        } else if(type == MOD) {
            try {
                mod.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            }
        } else if(type == STAFF) {
            try {
                staff.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            }
        } else if(type == ALERT) {
            try {
                alert.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            }
        } else if(type == VIP) {
            try {
                vip.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            }
        } else if(type == BMT) {
            try {
                bmt.remove(p);
            } catch(Exception ex) {
                // ignore this error, should be fine. I'll log it to the console anyways just for debug reasons
                LogUtils.warn("Encountered an exception: " + ex.getMessage() + " - should be fine. Just printing this to the console for debug reasons so I can fix errors faster.");
            }
        }
    }

    public static void clearStaffchatTypes() {
        staff.clear(); //clear the STAFF channel player list
        admin.clear(); //clear the ADMIN channel player list
        alert.clear(); //clear the ALERT channel player list
        mod.clear(); //clear the MOD channel player list
        bmt.clear(); //clear the BMT channel player list
        vip.clear(); //clear the VIP channel player list
    }

    public static void clearStaffchat(UUID p) {
        if(staff.contains(p)) staff.remove(p); //remove them from the channel "staff"
        if(admin.contains(p)) admin.remove(p); //remove them from the channel "admin"
        if(alert.contains(p)) alert.remove(p); //remove them from the channel "alert"
        if(mod.contains(p)) mod.remove(p); //remove them from the channel "mod"
        if(bmt.contains(p)) bmt.remove(p); //remove them from the channel "bmt"
        if(vip.contains(p)) vip.remove(p); //remove them from the channel "vip"
    }

}
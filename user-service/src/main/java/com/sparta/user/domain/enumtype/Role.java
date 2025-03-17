package com.sparta.user.domain.enumtype;

import lombok.Getter;

@Getter
public enum Role {
    // 각 권한 별 레벨설정
    DELIVERY(Authority.DELIVERY, 1),
    COMPANY(Authority.COMPANY, 2),
    HUB(Authority.HUB, 3),
    MASTER(Authority.MASTER, 4);

    private final String authority;
    private final int level;

    public static boolean isGreaterThen(Role role, Role currentUserRole) {
        if (role == null || currentUserRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        return role.level > currentUserRole.level;
    }


    Role(String authority, int level) {
        this.authority = authority;
        this.level = level;
    }

    public static class Authority {
        public static final String HUB = "ROLE_HUB";
        public static final String COMPANY = "ROLE_COMPANY";
        public static final String DELIVERY = "ROLE_DELIVERY";
        public static final String MASTER = "ROLE_MASTER";
    }
}

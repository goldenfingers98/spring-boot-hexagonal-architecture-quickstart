package tn.ksoftwares.auth.persistence.entity.constraint;

public enum UserConstraint {
    UNIQUE_EMAIL,
    UNIQUE_USERNAME;

    public static UserConstraint getConstraintByName(String message) {
        for(UserConstraint it: UserConstraint.values()) {
            if (message.contains(it.name())) {
                return it;
            }
        }
        return null;
    }
}

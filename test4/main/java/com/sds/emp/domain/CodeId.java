package com.sds.emp.domain;

/**
 * This CodeId entity class contains all attributes of
 * CODE Key and putter,setter methods of them.
 * @author Taeho Kim
 */
public class CodeId implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String codeType;
    private String code;

    public CodeId() {
    }

    public CodeId(String codeType, String code) {
        this.codeType = codeType;
        this.code = code;
    }

    public String getCodeType() {
        return this.codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CodeId))
            return false;
        CodeId castOther = (CodeId) other;

        return ((this.getCodeType() == castOther.getCodeType()) || (this
            .getCodeType() != null
            && castOther.getCodeType() != null && this.getCodeType().equals(
            castOther.getCodeType())))
            && ((this.getCode() == castOther.getCode()) || (this.getCode() != null
                && castOther.getCode() != null && this.getCode().equals(
                castOther.getCode())));
    }

    public int hashCode() {
        int result = 17;

        int codeTypeHashCode = 0;

        if (getCodeType() != null)
            codeTypeHashCode = this.getCodeType().hashCode();

        result = 37 * result + codeTypeHashCode;

        int codeHashCode = 0;
        if (getCode() == null)
            codeHashCode = this.getCode().hashCode();
        result = 37 * result + codeHashCode;
        return result;
    }

}

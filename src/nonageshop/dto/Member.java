package nonageshop.dto;

import java.util.Date;

public class Member {
    private String id; /* 회원아이디 */
    private String pwd; /* 회원암호 */
    private String name; /* 회원이름 */
    private String email; /* 회원이메일 */
    private String zip_num; /* 우편번호 */
    private String address; /* 주소 */
    private String phone; /* 전화번호 */
    private String leave_yn; /* 탈퇴여부 */
    private Date join_date;/* 가입일 */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip_num() {
        return zip_num;
    }

    public void setZip_num(String zip_num) {
        this.zip_num = zip_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLeave_yn() {
        return leave_yn;
    }

    public void setLeave_yn(String leave_yn) {
        this.leave_yn = leave_yn;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    @Override
    public String toString() {
        return String.format(
                "Member [id=%s, pwd=%s, name=%s, email=%s, zip_num=%s, address=%s, phone=%s, leave_yn=%s, join_date=%s]",
                id, pwd, name, email, zip_num, address, phone, leave_yn, join_date);
    }

}

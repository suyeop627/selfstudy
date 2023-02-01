package dto;

import lombok.Data;

@Data
public class MemberDto extends BookingDto {
  private String m_id;
  private String m_pwd;
  private String m_name;
  private String m_phone;
  private String m_birth;
  private String m_account;

}

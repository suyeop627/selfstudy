package dto;

import lombok.Data;

@Data
public class TicketDto extends ScheduleDto {
  private int t_no;
  private int s_no;
  private int p_no;
  private int b_no;
  private String m_id;
}

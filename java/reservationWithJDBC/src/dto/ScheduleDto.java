package dto;

import lombok.Data;

@Data
public class ScheduleDto extends PerformanceDto {
  private int s_no;
  private int p_no;
  private String s_date;
  private String s_start;
  private String s_end;
  private String s_hall;
  private int s_seats;

}

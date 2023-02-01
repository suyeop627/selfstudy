package dto;

import lombok.Data;

@Data
public class BookingDto extends TicketDto  {
  private int b_no;
  private String m_id;
  private String b_seat;
}

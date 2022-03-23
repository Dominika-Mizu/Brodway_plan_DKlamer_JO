public class musical {
//dane
	protected String title;
	protected String author;
	protected char[] date; //data premiery
	protected int numberOfTracks;
	protected float ticketPrice;
	protected int duration; //w minutach

//konstruktor
	protected musical() {
		title = "undefined";
		author = "undefined";
		date = new char[8];
		numberOfTracks = 0;
		ticketPrice = 0;
		duration = 0;
	}
	
//setery
	protected void setTitle(String Title) {
		title = Title;
	}
	protected void setAuthor(String Author) {
		author = Author;
	}
	protected void setDate(char[] Date) {
		System.arraycopy(Date, 0, date, 0, 8);
	}
	protected void setNumberOfTracks(int NumberOfTracks) {
		numberOfTracks = NumberOfTracks;
	}
	protected void setTicketPrice(float TicketPrice) {
		ticketPrice = TicketPrice;
	}
	protected void setDuration(int Duration) {
		duration = Duration;
	}

//getery
	protected String getTitle(){
		return title;
	}
	protected String getAuthor() {
		return author;
	}
	protected char[] getDate() {
		return date;
	}
	protected int getNumberOfTracks() {
		return numberOfTracks;
	}
	protected float getTicketPrice() {
		return ticketPrice;
	}
	protected int getDuration() {
		return duration;
	}
}

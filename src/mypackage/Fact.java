package mypackage;

public class Fact {

	public int E, TWO, FOUR, EIGHT, SIXTEEN, THIRTYTWO, SIXTYFOUR,
	ONEHUNDREDTWENTYEIGHT, TWOHUNDREDFIFTYSIX, FIVEHUNDREDTWELVE,
	ONETHOUSANDTWENTYFOUR, TWOTHOUSANDFOURTHYEIGHT, V;
	public String M;

	public Fact()
	{
		
	}

	
public Fact(int e, int tWO, int fOUR, int eIGHT, int sIXTEEN, int tHIRTYTWO,
		int sIXTYFOUR, int oNEHUNDREDTWENTYEIGHT, int tWOHUNDREDFIFTYSIX,
		int fIVEHUNDREDTWELVE, int oNETHOUSANDTWENTYFOUR,
		int tWOTHOUSANDFOURTHYEIGHT, int v, String m) {
	E = e;
	TWO = tWO;
	FOUR = fOUR;
	EIGHT = eIGHT;
	SIXTEEN = sIXTEEN;
	THIRTYTWO = tHIRTYTWO;
	SIXTYFOUR = sIXTYFOUR;
	ONEHUNDREDTWENTYEIGHT = oNEHUNDREDTWENTYEIGHT;
	TWOHUNDREDFIFTYSIX = tWOHUNDREDFIFTYSIX;
	FIVEHUNDREDTWELVE = fIVEHUNDREDTWELVE;
	ONETHOUSANDTWENTYFOUR = oNETHOUSANDTWENTYFOUR;
	TWOTHOUSANDFOURTHYEIGHT = tWOTHOUSANDFOURTHYEIGHT;
	M = m;
	V = v;
	
}

@Override
public String toString()
{
	return "Grid("+E+","+TWO+","+FOUR+","+EIGHT+","+SIXTEEN+","+THIRTYTWO+","+SIXTYFOUR+","+ONEHUNDREDTWENTYEIGHT+","+TWOHUNDREDFIFTYSIX+","+FIVEHUNDREDTWELVE+","+ONETHOUSANDTWENTYFOUR+","+TWOTHOUSANDFOURTHYEIGHT+","+'"'+M+'"'+","+V+")";
			
	
}



}

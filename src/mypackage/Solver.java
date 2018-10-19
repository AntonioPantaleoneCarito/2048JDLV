package mypackage;

import mypackage.Board.Tile;
import java.util.List;
import java.util.ArrayList;

public  class Solver{

private int MAX_INT= 131072 ;
private int MAX_SCORE= 59577 ;
public List<Fact> facts= new ArrayList<Fact>();
public List<String> res= new ArrayList<String>();
Solver(){
}
Solver(Move m){
facts.clear();
res.clear();
facts.add(generateFactS(m.getGridUP(),m.getScoreUP(), "UP" ));
facts.add(generateFactS(m.getGridDOWN(),m.getScoreDOWN(), "DOWN" ));
facts.add(generateFactS(m.getGridLEFT(),m.getScoreLEFT(), "LEFT" ));
facts.add(generateFactS(m.getGridRIGHT(),m.getScoreRIGHT(), "RIGHT" ));
res.add(DLV());
}
public String DLV(){

	// ---- START - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Creation newProgram JDLV module.");
it.unical.mat.wrapper.DLVInputProgram _JDLV_PROGRAM_newProgram=new it.unical.mat.wrapper.DLVInputProgramImpl();
_JDLV_PROGRAM_newProgram.cleanText();
java.lang.StringBuffer _JDLV_PROGRAM_BUFFER_newProgram=new java.lang.StringBuffer();
it.unical.mat.wrapper.DLVInvocation _JDLV_INVOCATION_newProgram;

	// ---- END - startProgram ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_newProgram.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(facts,"Grid"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'facts::Grid' in module newProgram:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(facts,"Grid"), 0));

	// ---- END - addInMapping ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'res::Best' in module newProgram.");

	// ---- START - prepareJDLVCall ---- 
try{

_JDLV_INVOCATION_newProgram=it.unical.mat.wrapper.DLVWrapper.getInstance().createInvocation("./dlv.mingw-odbc.exe");
_JDLV_PROGRAM_newProgram.addText(_JDLV_PROGRAM_BUFFER_newProgram.toString());
_JDLV_PROGRAM_newProgram.addText("Best(M) v notBest(M) :- Grid(E, TWO, FOUR, EIGHT, SIXTEEN, THIRTYTWO, SIXTYFOUR, ONEHUNDREDTWENTYEIGHT, TWOHUNDREDFIFTYSIX, FIVEHUNDREDTWELVE, ONETHOUSANDTWENTYFOUR, TWOTHOUSANDFOURTHYEIGHT, M, V)."+'\n');
_JDLV_PROGRAM_newProgram.addText("NotEmptyCell(NEC, M) :- NEC = #sum{A,B,C,D,E,F,G,H,I,L : Grid(_, A, B, C, D, E, F, G, H, I, L, _, M, _)}."+'\n');
_JDLV_PROGRAM_newProgram.addText(":- #count{M : Best(M)} > 1."+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, TWO, FOUR, EIGHT, SIXTEEN, THIRTYTWO, SIXTYFOUR, ONEHUNDREDTWENTYEIGHT, TWOHUNDREDFIFTYSIX, FIVEHUNDREDTWELVE, ONETHOUSANDTWENTYFOUR, TWOTHOUSANDFOURTHYEIGHT, M, _), NotEmptyCell(NEC, M). [NEC:13]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, TWO, _, _, _, _, _, _, _, _, _, _, M, _). [TWO:12]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, FOUR, _, _, _, _, _, _, _, _, _, M, _). [FOUR:11]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, EIGHT, _, _, _, _, _, _, _, _, _, M, _). [EIGHT:10]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, SIXTEEN, _, _, _, _, _, _, _, _, M, _). [SIXTEEN:9]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, THIRTYTWO, _, _, _, _, _, _, _, M, _). [THIRTYTWO:8]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, _, _, SIXTYFOUR, _, _, _, _, _, M, _). [SIXTYFOUR:7]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, _, _, _, ONEHUNDERDTWENTYEIGHT, _, _, _, _, M, _). [ONEHUNDREDTWENTYEIGHT:6]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, _, _, _, _, TWOHUNDREDFIFTYSIX, _, _, _, M, _). [TWOHUNDREDFIFTYSIX:5]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, _, _, _, _, _, FIVEHUNDREDTWELVE, _, _, M, _). [FIVEHUNDREDTWELVE:4]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, _, _, _, _, _, _, ONETHOUSANDTWENTYFOUR, _, M, _). [ONETHOUSANDTWENNTYFOUR:3]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, _, _, _, _, _, _, _, TWOTHOUSANDFOURTHYEIGHT, M, _). [TWOTHOUSANDFOURTHYEIGHT:2]"+'\n');
_JDLV_PROGRAM_newProgram.getFiles().clear();
_JDLV_INVOCATION_newProgram.setNumberOfModels(1);
_JDLV_PROGRAM_BUFFER_newProgram.append("");
_JDLV_INVOCATION_newProgram.setInputProgram(_JDLV_PROGRAM_newProgram);
it.unical.mat.wrapper.ModelBufferedHandler _BUFFERED_HANDLER_newProgram=new it.unical.mat.wrapper.ModelBufferedHandler(_JDLV_INVOCATION_newProgram);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Start execution newProgram program: executablePath='"+it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath()+"', options='"+_JDLV_INVOCATION_newProgram.getOptionsString()+"'"+'\n'+"Code execution: "+it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(_JDLV_INVOCATION_newProgram.getInputProgram().getCompleteText(),0));
_JDLV_INVOCATION_newProgram.run();
while(_BUFFERED_HANDLER_newProgram.hasMoreModels()){
it.unical.mat.wrapper.Model _temporary_JDLV_MODELnewProgram=_BUFFERED_HANDLER_newProgram.nextModel();
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(res, "Best",_temporary_JDLV_MODELnewProgram,String.class);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Process new answer_set"+ '\n' + "Results:"+ '\n'+ "res " + res.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(res,0));
}
if(_JDLV_INVOCATION_newProgram.haveModel()==false){
res.clear();
System.out.println( "No Answer Set" );
res.add( "noAS" );
}
if(!_JDLV_INVOCATION_newProgram.getErrors().isEmpty()){
throw new java.lang.RuntimeException(_JDLV_INVOCATION_newProgram.getErrors().get(0).getText());
}
}
catch(java.lang.Throwable _JDLV_EXCEPTION_newProgram){
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logErrorMessage(_JDLV_EXCEPTION_newProgram.getMessage());
}
_JDLV_PROGRAM_newProgram.cleanText();

	// ---- END - prepareJDLVCall ---- 
if(res.get( 0 )!= "noAS" ||res.size()!= 0 )
System.out.println( "Mossa scelta " +res);
return res.get( 0 );
}
public Fact generateFactS(int values[][],int score,String m){
int E, TWO, FOUR, EIGHT, SIXTEEN, THIRTYTWO, SIXTYFOUR, ONEHUNDREDTWENTYEIGHT, TWOHUNDREDFIFTYSIX, FIVEHUNDREDTWELVE, ONETHOUSANDTWENTYFOUR, TWOTHOUSANDFOURTHYEIGHT, V;
String M;
E= 0 ;
TWO= 0 ;
FOUR= 0 ;
EIGHT= 0 ;
SIXTEEN= 0 ;
THIRTYTWO= 0 ;
SIXTYFOUR= 0 ;
ONEHUNDREDTWENTYEIGHT= 0 ;
TWOHUNDREDFIFTYSIX= 0 ;
FIVEHUNDREDTWELVE= 0 ;
ONETHOUSANDTWENTYFOUR= 0 ;
TWOTHOUSANDFOURTHYEIGHT= 0 ;
V=score;
M=m;
for(int i= 0 ;i< 4 ;i++)
{
for(int j= 0 ;j< 4 ;j++)
{
 switch(values[i][j]){
 case  0 :
E++;
 break ;
 case  2 :
TWO++;
 break ;
 case  4 :
FOUR++;
 break ;
 case  8 :
EIGHT++;
 break ;
 case  16 :
SIXTEEN++;
 break ;
 case  32 :
THIRTYTWO++;
 break ;
 case  64 :
SIXTYFOUR++;
 break ;
 case  128 :
ONEHUNDREDTWENTYEIGHT++;
 break ;
 case  256 :
TWOHUNDREDFIFTYSIX++;
 break ;
 case  512 :
FIVEHUNDREDTWELVE++;
 break ;
 case  1024 :
ONETHOUSANDTWENTYFOUR++;
 break ;
 case  2048 :
TWOTHOUSANDFOURTHYEIGHT++;
 break ;
}

}
}
return  new Fact(E,TWO,FOUR,EIGHT,SIXTEEN,THIRTYTWO,SIXTYFOUR,ONEHUNDREDTWENTYEIGHT,TWOHUNDREDFIFTYSIX,FIVEHUNDREDTWELVE,ONETHOUSANDTWENTYFOUR,TWOTHOUSANDFOURTHYEIGHT,V,M);
}
}

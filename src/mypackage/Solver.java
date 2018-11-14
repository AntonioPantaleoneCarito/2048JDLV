package mypackage;

import mypackage.Board.Tile;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public  class Solver{

private int MAX_INT= 131072 ;
private int MAX_SCORE= 59577 ;
public List<Fact> facts= new ArrayList<Fact>();
public List<String> result= new ArrayList<String>();
public String strategy;
Move move;
Solver(){
}
Solver(Move m){
facts.clear();
result.clear();
strategy= "null" ;
 this .move=m;
if(!java.util.Arrays.deepEquals(m.getGridUP(),m.getGrid())&&m.getGridUP()!= null )
{
System.out.println( "gridUP e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridUP(),m.getScoreUP(), "UP" ));
}
if(!java.util.Arrays.deepEquals(m.getGridDOWN(),m.getGrid())&&m.getGridDOWN()!= null )
{
System.out.println( "gridDOWN e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridDOWN(),m.getScoreDOWN(), "DOWN" ));
}
if(!java.util.Arrays.deepEquals(m.getGridLEFT(),m.getGrid())&&m.getGridLEFT()!= null )
{
System.out.println( "gridLEFT e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridLEFT(),m.getScoreLEFT(), "LEFT" ));
}
if(!java.util.Arrays.deepEquals(m.getGridRIGHT(),m.getGrid())&&m.getGridRIGHT()!= null )
{
System.out.println( "gridRIGHT e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridRIGHT(),m.getScoreRIGHT(), "RIGHT" ));
}
result.add(DLV());
}
Solver(Move m,String s){
facts.clear();
result.clear();
 this .move=m;
if(!java.util.Arrays.deepEquals(m.getGridUP(),m.getGrid())&&m.getGridUP()!= null )
{
System.out.println( "gridUP e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridUP(),m.getScoreUP(), "UP" ));
}
if(!java.util.Arrays.deepEquals(m.getGridDOWN(),m.getGrid())&&m.getGridDOWN()!= null )
{
System.out.println( "gridDOWN e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridDOWN(),m.getScoreDOWN(), "DOWN" ));
}
if(!java.util.Arrays.deepEquals(m.getGridLEFT(),m.getGrid())&&m.getGridLEFT()!= null )
{
System.out.println( "gridLEFT e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridLEFT(),m.getScoreLEFT(), "LEFT" ));
}
if(!java.util.Arrays.deepEquals(m.getGridRIGHT(),m.getGrid())&&m.getGridRIGHT()!= null )
{
System.out.println( "gridRIGHT e Sorgente NON sono uguali" );
facts.add(generateFactS(m.getGridRIGHT(),m.getScoreRIGHT(), "RIGHT" ));
}
 this .strategy=s;
result.add(DLV());
}
public String DLV(){
List<String> res= new ArrayList<String>();
for(Fact f:facts)
System.out.println(f+ "." );
System.out.println( "CommonChoice(" + '"' +strategy+ '"' + ")." );
if(move.getPrevious()!= null )
System.out.println( "PreviousChoice(" + '"' +move.getPrevious().name()+ '"' + ")." );
writeFactsFile(facts,move);

	// ---- START - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Creation newProgram JDLV module.");
it.unical.mat.wrapper.DLVInputProgram _JDLV_PROGRAM_newProgram=new it.unical.mat.wrapper.DLVInputProgramImpl();
_JDLV_PROGRAM_newProgram.cleanText();
java.lang.StringBuffer _JDLV_PROGRAM_BUFFER_newProgram=new java.lang.StringBuffer();
it.unical.mat.wrapper.DLVInvocation _JDLV_INVOCATION_newProgram;

	// ---- END - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'res::Best' in module newProgram.");

	// ---- START - prepareJDLVCall ---- 
try{

_JDLV_INVOCATION_newProgram=it.unical.mat.wrapper.DLVWrapper.getInstance().createInvocation("./dlv.mingw-odbc.exe");
_JDLV_PROGRAM_newProgram.addText(_JDLV_PROGRAM_BUFFER_newProgram.toString());
_JDLV_PROGRAM_newProgram.addText("Best(M) v notBest(M) :- Grid(_, _, _, _, _, _, _, _, _, _, _, _, M, _)."+'\n');
_JDLV_PROGRAM_newProgram.addText(":- #count{M : Best(M)} = 0."+'\n');
_JDLV_PROGRAM_newProgram.addText(":- #count{M : Best(M)} > 1."+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(\"DOWN\"), PreviousChoice(\"UP\"). [1:4]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(\"UP\"), PreviousChoice(\"DOWN\"). [1:4]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(\"LEFT\"), PreviousChoice(\"RIGHT\"). [1:4]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(\"RIGHT\"), PreviousChoice(\"LEFT\"). [1:4]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), not CommonChoice(M). [1:1]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"LEFT\"), MaxList(V, _, _, _), GridPos(V, V, _, _, _, _, _, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"LEFT\"), MaxList(V, _, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, V, V, _, _, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"RIGHT\"), MaxList(V, _, _, _), GridPos(_, _, V, V, _, _, _, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"RIGHT\"), MaxList(V, _, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, _, _, V, V, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"UP\"), MaxList(V, _, _, _), GridPos(V, _, _, _, V, _, _, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"UP\"), MaxList(V, _, _, _), GridPos(_, _, _, V, _, _, _, V, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"DOWN\"), MaxList(V, _, _, _), GridPos(_, _, _, _, _, _, _, _, V, _, _, _, V, _, _, _, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"DOWN\"), MaxList(V, _, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, V, _, _, _, V, \"ORIGINE\", _). [1:30]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"LEFT\"), MaxList(V, K, _, _), GridPos(V, K, K, _, _, _, _, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"LEFT\"), MaxList(V, K, _, _), GridPos(V, _, _, _, K, K, _, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"LEFT\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, V, K, K, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"LEFT\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, K, K, _, _, V, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"RIGHT\"), MaxList(V, K, _, _), GridPos(_, K, K, V, _, _, _, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"RIGHT\"), MaxList(V, K, _, _), GridPos(_, _, _, V, _, _, K, K, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"RIGHT\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, _, K, K, V, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"RIGHT\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, K, K, _, _, _, V, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"UP\"), MaxList(V, K, _, _), GridPos(V, _, _, _, K, _, _, _, K, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"UP\"), MaxList(V, K, _, _), GridPos(V, K, _, _, _, K, _, _, K, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"UP\"), MaxList(V, K, _, _), GridPos(_, _, _, V, _, _, _, K, _, _, _, K, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"UP\"), MaxList(V, K, _, _), GridPos(_, _, K, V, _, _, K, _, _, _, _, _, _, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"DOWN\"), MaxList(V, K, _, _), GridPos(_, _, _, _, K, _, _, _, K, _, _, _, V, _, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"DOWN\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, K, _, _, V, K, _, _, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"DOWN\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, K, _, _, _, K, _, _, _, V, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(\"DOWN\"), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, K, _, _, _, K, V, \"ORIGINE\", _). [1:29]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(V, B, C, D, E, F, G, H, I, L, N, O, P, Q, R, S, M, _), B >= C, C >= D, C >= D, E >= F, F >= G, G >= H, I >= L, L >= N, N >= O, P >= Q, Q >= R, R >= S. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(V, B, C, D, E, F, G, H, I, L, N, O, P, Q, R, S, M, _), E >= I, I >= P, B >= F, F >= L, L >= Q, C >= G, G >= N, N >= R, D >= H, H >= O, O >= S. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(A, B, C, V, E, F, G, H, I, L, N, O, P, Q, R, S, M, _), C >= B, B >= A, H >= G, G >= F, F >= E, O >= N, N >= L, L >= I, S >= R, R >= Q, Q >= P. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(A, B, C, V, E, F, G, H, I, L, N, O, P, Q, R, S, M, _), A >= O, O >= S, C >= G, G >= N, N >= R, B >= F, F >= L, L >= Q, A >= E, E >= I, I >= P. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(A, B, C, D, E, F, G, H, I, L, N, O, V, Q, R, S, M, _), A >= B, B >= C, C >= D, E >= F, F >= G, G >= H, I >= L, L >= N, N >= O, Q >= R, R >= S. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(A, B, C, D, E, F, G, H, I, L, N, O, V, Q, R, S, M, _), I >= E, E >= A, Q >= L, L >= F, F >= B, R >= N, N >= G, G >= C, S >= O, O >= H, H >= D. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(A, B, C, D, E, F, G, H, I, L, N, O, P, Q, R, V, M, _), D >= C, C >= B, B >= A, H >= G, G >= F, F >= E, O >= N, N >= L, L >= I, R >= Q, Q >= P. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(A, B, C, D, E, F, G, H, I, L, N, O, P, Q, R, V, M, _), O >= H, H >= D, R >= N, N >= G, G >= C, Q >= L, L >= F, F >= B, P >= I, I >= E, E >= A. [1:23]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(V, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:24]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(_, _, _, V, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:24]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, V, _, _, _, M, _). [1:24]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, _, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, V, M, _). [1:24]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(V, K, _, _, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(V, _, _, _, K, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(_, _, K, V, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(_, _, _, V, _, _, _, K, _, _, _, _, _, _, _, _, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, V, K, _, _, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, K, _, _, _, V, _, _, _, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, _, _, K, V, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, _, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, K, _, _, _, V, M, _). [1:25]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(V, K, J, _, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(V, _, _, _, K, _, _, _, J, _, _, _, _, _, _, _, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(_, J, K, V, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(_, _, _, V, _, _, _, K, _, _, _, J, _, _, _, _, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, V, K, J, _, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(_, _, _, _, J, _, _, _, K, _, _, _, V, _, _, _, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, _, J, K, V, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, _), GridPos(_, _, _, _, _, _, _, J, _, _, _, K, _, _, _, V, M, _). [1:26]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(V, K, J, Y, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(V, _, _, _, K, _, _, _, J, _, _, _, Y, _, _, _, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(Y, J, K, V, _, _, _, _, _, _, _, _, _, _, _, _, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(_, _, _, V, _, _, _, K, _, _, _, J, _, _, _, Y, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, V, K, J, Y, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(Y, _, _, _, J, _, _, _, K, _, _, _, V, _, _, _, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(_, _, _, _, _, _, _, _, _, _, _, _, Y, J, K, V, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ notBest(M), MaxList(V, K, J, Y), GridPos(_, _, _, Y, _, _, _, J, _, _, _, K, _, _, _, V, M, _). [1:27]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, TWO, _, _, _, _, _, _, _, _, _, _, M, _). [TWO:12]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, FOUR, _, _, _, _, _, _, _, _, _, M, _). [FOUR:12]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, EIGHT, _, _, _, _, _, _, _, _, M, _). [EIGHT:12]"+'\n');
_JDLV_PROGRAM_newProgram.addText(":~ Best(M), Grid(_, _, _, _, SIXTEEN, _, _, _, _, _, _, _, M, _). [SIXTEEN:12]"+'\n');
_JDLV_PROGRAM_newProgram.getFiles().clear();
_JDLV_PROGRAM_newProgram.addFile("./facts.txt");
_JDLV_INVOCATION_newProgram.setNumberOfModels(1);
_JDLV_PROGRAM_BUFFER_newProgram.append("");
_JDLV_INVOCATION_newProgram.setInputProgram(_JDLV_PROGRAM_newProgram);
it.unical.mat.wrapper.ModelBufferedHandler _BUFFERED_HANDLER_newProgram=new it.unical.mat.wrapper.ModelBufferedHandler(_JDLV_INVOCATION_newProgram);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Start execution newProgram program: executablePath='"+it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath()+"', options='"+_JDLV_INVOCATION_newProgram.getOptionsString()+"'"+'\n'+"Code execution: "+it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(_JDLV_INVOCATION_newProgram.getInputProgram().getCompleteText(),0)+'\n'+"Files execution: ./facts.txt");
_JDLV_INVOCATION_newProgram.run();
while(_BUFFERED_HANDLER_newProgram.hasMoreModels()){
it.unical.mat.wrapper.Model _temporary_JDLV_MODELnewProgram=_BUFFERED_HANDLER_newProgram.nextModel();
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(res, "Best",_temporary_JDLV_MODELnewProgram,String.class);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Process new answer_set"+ '\n' + "Results:"+ '\n'+ "res " + res.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(res,0));
}
if(_JDLV_INVOCATION_newProgram.haveModel()==false){
System.out.println( "No Answer Set" );
result.add( "noAS" );
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
if(res.size()!= 0 )
{
System.out.println( "Mossa scelta " +res.get( 0 ));
if(move.getPrevious()!= null )
System.out.println( "PreviousChoice(" + '"' +move.getPrevious().name()+ '"' + ")." );
return res.get( 0 );
}
return  "ALTRO" ;
}
public  void  writeFactsFile(List<Fact> source,Move m){
try
{
FileOutputStream fos= new FileOutputStream( "./facts.txt" );
PrintStream write= new PrintStream(fos);
for(Fact f:source)
{
write.println(f+ "." );
}
if(!strategy.equals( "null" ))
{
write.println( "CommonChoice(" + '"' +strategy+ '"' + ")." );
}
if(m.getPrevious()!= null )
{
write.println( "PreviousChoice(" + '"' +m.getPrevious().name()+ '"' + ")." );
}
int gridT[][]=m.getGrid();
int scoreT=m.getScore();
List<Integer> maxL= new ArrayList<Integer>();
if(gridT!= null )
{
write.print( "GridPos(" );
for(int i= 0 ;i< 4 ;i++)
for(int j= 0 ;j< 4 ;j++)
{
write.print(gridT[i][j]+ "," );
}
write.println( '"' + "ORIGINE" + '"' + "," +scoreT+ ")." );
}
for(int i= 0 ;i< 4 ;i++)
for(int j= 0 ;j< 4 ;j++)
{
maxL.add(gridT[i][j]);
}
if(maxL.size()> 3 )
{
Collections.sort(maxL,Collections.reverseOrder());
write.println( "Max(" +maxL.get( 0 )+ ")." );
write.print( "MaxList(" +maxL.get( 0 ));
for(int i= 1 ;i< 4 ;i++)
write.print( "," +maxL.get(i));
write.println( ")." );
}
scoreT=m.getScoreUP();
gridT=m.getGridUP();
if(gridT!= null )
{
write.print( "GridPos(" );
for(int i= 0 ;i< 4 ;i++)
for(int j= 0 ;j< 4 ;j++)
{
write.print(gridT[i][j]+ "," );
}
write.println( '"' + "UP" + '"' + "," +scoreT+ ")." );
}
gridT=m.getGridDOWN();
scoreT=m.getScoreDOWN();
if(gridT!= null )
{
write.print( "GridPos(" );
for(int i= 0 ;i< 4 ;i++)
for(int j= 0 ;j< 4 ;j++)
{
write.print(gridT[i][j]+ "," );
}
write.println( '"' + "DOWN" + '"' + "," +scoreT+ ")." );
}
gridT=m.getGridRIGHT();
scoreT=m.getScoreRIGHT();
if(gridT!= null )
{
write.print( "GridPos(" );
for(int i= 0 ;i< 4 ;i++)
for(int j= 0 ;j< 4 ;j++)
{
write.print(gridT[i][j]+ "," );
}
write.println( '"' + "RIGHT" + '"' + "," +scoreT+ ")." );
}
gridT=m.getGridLEFT();
scoreT=m.getScoreLEFT();
if(gridT!= null )
{
write.print( "GridPos(" );
for(int i= 0 ;i< 4 ;i++)
for(int j= 0 ;j< 4 ;j++)
{
write.print(gridT[i][j]+ "," );
}
write.println( '"' + "LEFT" + '"' + "," +scoreT+ ")." );
}
}
catch(IOException e){
System.out.println( "Errore: " +e);
System.exit( 1 );
}
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


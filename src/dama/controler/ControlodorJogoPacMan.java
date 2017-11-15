package dama.controler;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class ControlodorJogoPacMan {
	
	public int linha = 9;
	public int coluna = 9;
	ArrayList<OndePacManPassou> posicao = new ArrayList<OndePacManPassou>();
	public int linhaF = 7;
	public int colunaF = 15;
	boolean num;
	
	public void restart() {
		this.linha =3;
		this.coluna =3;
		this.linhaF =3;
		this.colunaF =15;
		posicao.removeAll(posicao);
	}
	public void Acabou(){
		if((linhaF == coluna) && (colunaF == linha)){
			restart();
		}
		
	}

	public int getTamanho() {
		// TODO Auto-generated method stub
		return 20;
	}

	public int getVelocidade() {
		// TODO Auto-generated method stub
		return 200;
	}

	public void PackManParaEsquerda() {
		if(!AquiNaoPodeAndar(linha, coluna -1))
			coluna--;
			posicao();
			posicaoPacMan();
			Acabou();
	}

	public void PackManParaDireita() {
		if(!AquiNaoPodeAndar(linha, coluna+1))
			coluna++;
			posicao();
			posicaoPacMan();
			Acabou();
	}

	public void PackManParaCima() {
		if(!AquiNaoPodeAndar(linha -1, coluna))
			linha--;
			posicao();
			posicaoPacMan();
			Acabou();
	}

	public void PackManParaBaixo() {
		if(!AquiNaoPodeAndar(linha + 1, coluna))
			linha++;
			posicao();
			posicaoPacMan();
			Acabou();
	}
	
	public void posicaoPacMan(){
		if(linhaF > this.coluna && colunaF > this.linha){
			if(!AquiNaoPodeAndar(colunaF -1, linhaF))
				FantasmaParaCima();
			else
				FantasmaParaEsquerda();
		}
		else if(linhaF < this.coluna && colunaF > this.linha){
			if(!AquiNaoPodeAndar(colunaF -1, linhaF))
				FantasmaParaCima();
			else
				FantasmaParaDireita();
		}
		else if(linhaF < this.coluna && colunaF < this.linha){
			if(!AquiNaoPodeAndar(colunaF + 1, linhaF))
				FantasmaParaBaixo();
			else
				FantasmaParaDireita();
		}
		else if(linhaF > this.coluna && colunaF < this.linha){
			if(!AquiNaoPodeAndar(colunaF + 1, linhaF))
				FantasmaParaBaixo();
			else
				FantasmaParaDireita();
		}
		else if(linhaF == this.coluna && colunaF > this.linha)
			FantasmaParaCima();
		else if(linhaF == this.coluna && colunaF < this.linha)
			FantasmaParaBaixo();
		else if(linhaF < this.coluna && colunaF == this.linha)
			FantasmaParaDireita();
		else if(linhaF > this.coluna && colunaF == this.linha)
			FantasmaParaEsquerda();
	}

	public boolean temParede(int linha, int coluna) {
		// TODO Auto-generated method stub
		return (linha == 0 || linha == (getTamanho() - 1)) || (coluna == 0 || coluna == (getTamanho() - 1))
				||(linha==2 && coluna==4) ||(linha==3 && coluna==4) ||(linha==4 && coluna==4) 
				||(linha==5 && coluna==4) ||(linha==6 && coluna==4) ||(linha==9 && coluna==4)
				||(linha==10 && coluna==4) ||(linha==11 && coluna==4) ||(linha==12 && coluna==4)
				||(linha==13 && coluna==4) ||(linha==14 && coluna==4) ||(linha==15 && coluna==4) 
				||(linha==16 && coluna==4)  ||(linha==6 && coluna==5) 
				||(linha==6 && coluna==6) ||(linha==6 && coluna==7) ||(linha==6 && coluna==8) 
				||(linha==7 && coluna==8) ||(linha==8 && coluna==8) ||(linha==9 && coluna==8) 
				||(linha==10 && coluna==8) ||(linha==11 && coluna==8) ||(linha==12 && coluna==8) 
				||(linha==13 && coluna==8) ||(linha==14 && coluna==8) ||(linha==15 && coluna==8);
	}
	public boolean AquiNaoPodeAndar(int linha, int coluna) {
		// TODO Auto-generated method stub
		return (linha==4 && coluna==2) ||(linha==4 && coluna==3) ||(linha==4 && coluna==4) 
				||(linha==4 && coluna==5) ||(linha==4 && coluna==6) ||(linha==4 && coluna==9)
				||(linha==4 && coluna==10) ||(linha==4 && coluna==11) ||(linha==4 && coluna==12)
				||(linha==4 && coluna==13) ||(linha==4 && coluna==14) ||(linha==4 && coluna==15) 
				||(linha==4 && coluna==16)  ||(linha==5 && coluna==6) 
				||(linha==6 && coluna==6) ||(linha==7 && coluna==6) ||(linha==8 && coluna==6) 
				||(linha==8 && coluna==7) ||(linha==8 && coluna==8) ||(linha==8 && coluna==9) 
				||(linha==8 && coluna==10) ||(linha==8 && coluna==11) ||(linha==8 && coluna==12) 
				||(linha==8 && coluna==13) ||(linha==8 && coluna==14) ||(linha==8 && coluna==15);
	}
	
	public void FantasmaParaEsquerda() {
		if(!AquiNaoPodeAndar(colunaF, linhaF -1))
			linhaF--;
	}

	public void FantasmaParaDireita() {
		if(!AquiNaoPodeAndar(colunaF, linhaF+1))
			linhaF++;
	}

	public void FantasmaParaCima() {
		if(!AquiNaoPodeAndar(colunaF -1, linhaF))
			colunaF--;
	}

	public void FantasmaParaBaixo() {
		if(!AquiNaoPodeAndar(colunaF + 1, linhaF))
			colunaF++;
	}

	public boolean temBolinha(int linha, int coluna) {
		boolean num=false;
		for(int i=0; i<this.posicao.size();i++){
			if((posicao.get(i).x == coluna && posicao.get(i).y == linha))
				num = true;
		}
		if(num==true || (temParede(linha , coluna)))
			return false;
		else
			return true;
	}
	
	public void posicao(){
		boolean add =false;
		for(int i=0;i<this.posicao.size();i++){
			if((posicao.get(i).x == this.linha && posicao.get(i).y == this.coluna))
				add = true;
		}
		if(add==false){
			OndePacManPassou x = new OndePacManPassou(this.linha,this.coluna);
			this.posicao.add(x);
		}
	}
	
	public boolean temPackMan(int linha, int coluna) {
		this.num =true;
		return linha == this.linha && coluna == this.coluna;
		
		
	}
	
	

	public boolean temFantasma(int linha, int coluna) {
		// TODO Auto-generated method stub
		return linha == linhaF && coluna==colunaF ;
	}
	
	

	public boolean terminado() {
		// TODO Auto-generated method stub
		return false;
	}

}

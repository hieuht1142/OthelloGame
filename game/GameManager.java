/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class GameManager {
    //khoi dau game quy dinh =0:chua co co,=1 den,=2 trang
    public int[][] getStartBoard(){
        int[][] board=new int[8][8];
        for(int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                board[i][j]=0;
            }
        }
        board[3][3]=2;
        board[4][4]=2;
        board[4][3]=1;
        board[3][4]=1;
        return board;
    }
    
    //kiem tra game ket thuc chua
    public boolean isGameFinished(int[][] board){
        return !(isStillMove(board,1)||isStillMove(board, 2));
    }
    
    //kiem tra con nuoc di hay khong
    public boolean isStillMove(int[][] board,int player){
        return getPossibleMoves(board,player).size()>0;
    }
    
    //kiem tra cac nuoc di co the di
    public ArrayList<Point> getPossibleMoves(int[][] board,int player) {
        ArrayList<Point> moveList =new ArrayList<>();
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if(canPlay(board,player,i,j)){
                    moveList.add(new Point(i, j));
                }
            }
        }
        return moveList; 
    }
    
    //kiem tra diem(i,j) nguoi choi player co di duoc khong. oplayer nguoi con lai
    public boolean canPlay(int[][] board,int player,int i,int j){
        if (board[i][j]!=0) return false;
        int move_i,move_j,count;//count dem so quan co cua doi thu
        
        int oplayer;//nguoi con lai
        if(player==1) oplayer=2;
        else oplayer=1;
        
        //kiem tra ben tren diem(i,j)
        move_i=i-1;//hang giam dan
        move_j=j;//cot khong doi
        count=0;
        while( (move_i>0) && (board[move_i][move_j]==oplayer)){
            move_i--;
            count++;
        }
        if(count>0&&board[move_i][move_j]==player&&move_i>=0) return true;
        
        //kiem tra ben duoi diem(i,j)
        move_i=i+1;//hang tang dan
        move_j=j;//cot khong doi
        count=0;
        while( (move_i<7) && (board[move_i][move_j]==oplayer)){
            move_i++;
            count++;
        }
        if(count>0&&board[move_i][move_j]==player&&move_i<=7) return true;
        
        //kiem tra ben trai diem(i,j)
        move_i=i;//hang khong doi
        move_j=j-1;//cot giam dan doi
        count=0;
        while( (move_j>0) && (board[move_i][move_j]==oplayer)){
            move_j--;
            count++;
        }
        if(count>0&&board[move_i][move_j]==player&&move_j>=0) return true;
        
         //kiem tra ben phai diem(i,j)
        move_i=i;//hang khong doi
        move_j=j+1;//cot tang dan doi
        count=0;
        while( (move_j<7) && (board[move_i][move_j]==oplayer)){
            move_j++;
            count++;
        }
        if(count>0&&board[move_i][move_j]==player&&move_j<=7) return true;
       
        //kiem tra duong cheo  tren-trai
        move_i=i-1;//hang giam
        move_j=j-1;//cot giam
        count=0;
        while(move_i>0 &&move_j>0 && board[move_i][move_j]==oplayer){
            count++;
            move_i--;
            move_j--;
        }
        if (count>0&&board[move_i][move_j]==player&&move_i>=0&&move_j>=0) return true;
           
        //kiem tra duong cheo duoi-phai
        move_i=i+1;//hang tang
        move_j=j+1;//cot tang 
        count=0;
        while(move_i<7 &&move_j<7 && board[move_i][move_j]==oplayer){
            count++;
            move_i++;
            move_j++;
        }
        if (count>0&&board[move_i][move_j]==player&&move_i<=7&&move_j<=7) return true;
        
        //kiem tra duong cheo sang tren-phai
        move_i=i-1;//hang giam
        move_j=j+1;//cot tang dan doi
        count=0;
        while(move_i>0 &&move_j<7 && board[move_i][move_j]==oplayer){
            count++;
            move_i--;
            move_j++;
        }
        if (count>0 && board[move_i][move_j]==player && move_i>=0 && move_j<=7) return true;
        
        //kiem tra duong cheo sang duoi-trai
        move_i=i+1;//hang giam
        move_j=j-1;//cot tang dan doi
        count=0;
        while(move_i<7 &&move_j>0 && board[move_i][move_j]==oplayer){
            count++;
            move_i++;
            move_j--;
        }
        if (count>0 && board[move_i][move_j]==player && move_i<=7 && move_j>=0) return true;
        return false;
    }
    
    
    //kiem tra nguoi chien thang
    public int getWinner(int[][] board){
        if(!isGameFinished(board)){
            return -1;
        }
        else{
        //tinh tong so quan co cua moi nguoi
            int discPlayer1=getPlayerTotalDisc(board,1);
            int discPlayer2=getPlayerTotalDisc(board,2);
            if(discPlayer1==discPlayer2){
                return 0;
            }
            else if(discPlayer1>discPlayer2){
                return 1;
            }
            else return 2;
        }
    }
    
    
    //dem so quan co cua tung nguoi
    public int getPlayerTotalDisc(int[][] board,int player) {
        int number=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if (board[i][j]==player){
                    number++;
                }
            }
        }
        return number;   
    }
     
    //tinh tong so quan co tren ban co
    public int getTotalDisc(int[][] board){
        int count=0;
        for(int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (board[i][j]!=0) count++;
            }
        }
        return count;
    }
    //lay Point(i,j) cua nuoc di
    public Point getMovePoint(int[][] beforeBoard,int[][] afterBoard){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(beforeBoard[i][j]==0 && afterBoard[i][j]!=0){
                    return new Point(i,j);
                }
            }
        }
        return null;
    }
    
    //cap nhat board sau buoc move
    public int[][] updateBoard(int[][] board,Point move,int player){
        int[][] updateBoard=new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                updateBoard[i][j] = board[i][j];
            }
        }
        updateBoard[move.x][move.y]=player;
        ArrayList<Point> r=reversePoints(board, player,move.x, move.y);
        //cap nhat nhung diem bi doi mau
        for (Point p:r){
            updateBoard[p.x][p.y]=player;
        }
        return updateBoard;
    } 
    //tra ve ArrayList<Point> cac diem  doi mau sau buoc di (i,j)
    public ArrayList<Point> reversePoints(int[][] board,int player,int i,int j){
        ArrayList<Point>  reverseList =new ArrayList<>();
        int move_i,move_j;
        
        int oplayer;//nguoi con lai
        if(player==1) oplayer=2;
        else oplayer=1;
        
        //them cac quan co dao nguoc phia tren
        ArrayList<Point> m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i-1;
        move_j=j;
        while(move_i>0&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i--;
            
        }
        if(m.size()>0&&move_i>=0&&board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        //them cac quan co dao nguoc  phia duoi
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i+1;
        move_j=j;
        while(move_i<7&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i++;
        }
        if(m.size()>0&&move_i<=7&&board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        //cac quan co dao nguoc phia trai
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i;
        move_j=j-1;
        while(move_j>0&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j--;
        }
        if(m.size()>0&&move_j>=0&&board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        // cac quan co dao nguoc ben phai
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i;
        move_j=j+1;
        while(move_j<7&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j++;
        }
        if(m.size()>0&&move_j<=7&&board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        //them cac quan co dao nguoc duong cheo tren-trai
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i-1;
        move_j=j-1;
        while(move_j>0 && move_i>0 && board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i--;
            move_j--;
        }
        if(m.size()>0 && move_i>=0 && move_j>=0 && board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        //them cac quan co dao nguoc  duong cheo duoi-phai
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i+1;
        move_j=j+1;
        while(move_j<7 && move_i<7 && board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i++;
            move_j++;
        }
        if(m.size()>0 && move_i<=7 && move_j<=7 && board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        //them cac quan co dao nguoc  duong cheo tren-phai
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i-1;
        move_j=j+1;
        while(move_i>0 && move_j<7 && board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i--;
            move_j++;
        }
        if(m.size()>0 && move_i>=0 && move_j<=7 && board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        //dao nguoc cac quan co duong cheo duoi-trai
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i+1;
        move_j=j-1;
        while( move_i<7 &&move_j>0 && board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i++;
            move_j--;
        }
        if(m.size()>0 && move_i<=7 && move_j>=0 && board[move_i][move_j]==player){
            reverseList.addAll(m);
        }
        
        return reverseList;
    }
    
    //tra ve tat ca quan co cua doi thu oplayer xung quanh diem (i,j)
    public ArrayList<Point> getDiscCompetitor(int[][] board,int player,int i,int j){
        ArrayList<Point> table=new ArrayList<>();
        int move_i,move_j;
        
        int oplayer;//nguoi con lai
        if(player==1) oplayer=2;
        else oplayer=1;
        
        //xet tren diem (i,j)
        ArrayList<Point> m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i-1;
        move_j=j;
        while(move_i>0&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i--;  
        }
        
        //kiem tra neu chua co thi them,nguoc lai thi bo qua
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        //xet duoi diem (i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i+1;
        move_j=j;
        while(move_i<7&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_i++;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        //xet ben trai diem(i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i;
        move_j=j-1;
        while(move_j>0&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j--;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        //xet ben phai diem (i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i;
        move_j=j+1;
        while(move_j<7&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j++;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        //xet duong cheo tren-trai diem (i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i-1;
        move_j=j-1;
        while(move_j>0&&move_i>0&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j--;
            move_i--;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        //xet duong cheo duoi-phai diem (i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i+1;
        move_j=j+1;
        while(move_j<7&&move_i<7&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j++;
            move_i++;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        //xet duong cheo tren-phai diem (i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i-1;
        move_j=j+1;
        while(move_j<7&&move_i>=0&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j++;
            move_i--;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        //xet duong cheo duoi-trai diem (i,j)
        m=new ArrayList<>();//luu tam thoi cac quan co co the doi mau
        move_i=i+1;
        move_j=j-1;
        while(move_j>0&&move_i<7&&board[move_i][move_j]==oplayer){
            m.add(new Point(move_i,move_j));
            move_j--;
            move_i++;
        }
        for(Point m1 :m){
            boolean appear=false;
            for (Point tb:table){
                if (m.equals(tb)){
                    appear=true;
                    break;
                }
            }
            if (appear==false) table.add(m1);
        }
        
        return table;
    }
    
    //tra ve nhung diem bien
    public ArrayList<Point> getBorder(int[][] board,int player){
        ArrayList<Point> borderList=new ArrayList<>();//luu tru cac diem bien
        int oplayer;//nguoi con lai
        if(player==1) oplayer=2;
        else oplayer=1;
        
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (board[i][j]==oplayer){
                   ArrayList<Point> pBorder=new ArrayList<>();
                   
                   if(i>0 &&board[i-1][j]==0) pBorder.add(new Point(i-1,j));//tren
                   if(i<7 &&board[i+1][j]==0) pBorder.add(new Point(i+1,j));//duoi
                   if(j>0 &&board[i][j-1]==0) pBorder.add(new Point(i,j-1));//trai
                   if(j<7 &&board[i][j+1]==0) pBorder.add(new Point(i,j+1));//phai
                   if(i>0&&j>0 &&board[i-1][j-1]==0) pBorder.add(new Point(i-1,j-1));//tren-trai
                   if(i<7&&j<7 &&board[i+1][j+1]==0) pBorder.add(new Point(i+1,j+1));//duoi-phai
                   if(i>0&&j<7 &&board[i-1][j+1]==0) pBorder.add(new Point(i-1,j+1));//tren-phai
                   if(i<7&&j>0 &&board[i+1][j-1]==0) pBorder.add(new Point(i+1,j-1));//duoi-trai
                   
                   //loai bo cac diem bi trung lap
                   //kiem tra diem do co trong borderList chua
                   for (Point pb:pBorder){
                       boolean appear=false;
                       for(Point b:borderList){
                           if (b.equals(pb)){
                               appear=true;
                               break;
                           }
                           
                       }
                       if(appear==false) borderList.add(pb);
                   }
                   
                   
               }    
           }
        }
        return borderList;
       }
}

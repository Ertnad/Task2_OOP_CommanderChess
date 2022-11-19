package ru.vsu.sc.tretyakov_d_s.chess;

public enum SideColor {
 BLUE , RED;
 
 public SideColor swapColor() {
	 if(this ==SideColor.BLUE) return SideColor.RED;
	 return  SideColor.BLUE;
 }
 public String getBetterString() {
	 if(this ==SideColor.BLUE) return "Blue";
	 return  "Red";

 }
 
}

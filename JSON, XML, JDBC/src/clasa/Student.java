package ro.ase.acs.clasa;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"nume", "nrMatricol", "note"})
public class Student {
	private String nume;
	private int nrMatricol;

	private boolean locBugetat;
	private int[] note;
	
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	@XmlElement(name="matricol")
	public int getNrMatricol() {
		return nrMatricol;
	}
	public void setNrMatricol(int nrMatricol) {
		this.nrMatricol = nrMatricol;
	}
	@XmlAttribute(name="buget")
	public boolean isLocBugetat() {
		return locBugetat;
	}
	public void setLocBugetat(boolean locBugetat) {
		this.locBugetat = locBugetat;
	}
	public int[] getNote() {
		return note;
	}
	public void setNote(int[] note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Student [nume=" + nume + ", nrMatricol=" + nrMatricol + ", locBugetat=" + locBugetat + ", note="
				+ Arrays.toString(note) + "]";
	}
}

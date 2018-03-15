public class DamData implements Comparable <DamData> {
   private String damName;
   private String fsc;
   private String damLevel;
   
   public DamData () {
      damName = null;
      fsc = null;
      damLevel = null;
   }
   
   public DamData (String damName, String fsc, String damLevel) {
      this.damName = damName;
      this.fsc = fsc;
      this.damLevel = damLevel;
   }
   
   public String getDamName() {
      return this.damName;
   }
   
   public String getFSC() {
      return this.fsc;
   }
   
   public String getDamLevel() {
      return this.damLevel;
   }
   
   public void setDamName(String damName) {
      this.damName = damName;
   }
   
   public void setFSC(String fsc) {
      this.fsc = fsc;
   }
   
   public void setDamLevel(String damLevel) {
      this.damLevel = damLevel;
   }
   
   public String toString() {
      return ("Dam name: " + this.damName + ", " +
              "FSC: " + this.fsc + ", " +
              "Dam level: " + this.damLevel);
   }
   
   /**
    * This subroutine is implemented from the Comparable interface
    * It is a tool to order the fields of a DamData object
    * @return The comparison of corresponding DamData fields
    */
   public int compareTo(DamData other) {
	    int i = damName.compareTo(other.damName);
	    if (i != 0) {
	    	return i;
	    }

	    i = fsc.compareTo(other.fsc);
	    if (i != 0) {
	    	return i;
	    }

	    return damLevel.compareTo(other.damLevel);
  }
}
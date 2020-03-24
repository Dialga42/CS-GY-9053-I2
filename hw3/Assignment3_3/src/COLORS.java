public enum COLORS {
    RED("RED"){
        public void colorCombo(){
            System.out.println("Primary color");
        }
    },
    BLUE("BLUE"){
        public void colorCombo(){
            System.out.println("Primary color");
        }
    },
    YELLOW("YELLOW"){
        public void colorCombo(){
            System.out.println("Primary color");
        }
    },
    GREEN("GREEN"){
        public void colorCombo(){
            System.out.println("Formed by combination of yellow and blue");
        }
    },
    BLACK("BLACK"){
        public void colorCombo(){
            System.out.println("Formed by combination of red yellow and blue");
        }
    };
    private String color;
    COLORS(String color){
        this.color=color;
    }
    public COLORS printColor() {
        System.out.println(color);
        return this;
    }
    public abstract void colorCombo();
}
package verifyaddress;

public class Address {
    private final String indistrito = "indistrito";
    private final String inconcelho = "inconcelho";
    private final String inlocal = "inlocal";
    private final String inrua = "inrua";
    private final String inporta = "inporta";
    private final String incodpos = "incodpos";
    
    private String distrito;
    private String concelho;
    private String local;
    private String rua;
    private String porta;
    private String codpos;
    
    public Address(String distrito, String concelho, String local, String rua, String porta, String codpos) {
        super();
        this.distrito = distrito;
        this.concelho = concelho;
        this.local = local;
        this.rua = rua;
        this.porta = porta;
        this.codpos = codpos;
    }
    
    public String toString() {
        return  indistrito + "=" + this.distrito.replace(' ', '+') + "&" +
                inconcelho + "=" + this.concelho.replace(' ', '+') + "&" +
                inlocal + "=" + this.local.replace(' ', '+') + "&" +
                inrua + "=" + this.rua.replace(' ', '+') + "&" +
                inporta + "=" + this.porta.replace(' ', '+') + "&" +
                incodpos + "=" + this.codpos.replace(' ', '+');
    }
}

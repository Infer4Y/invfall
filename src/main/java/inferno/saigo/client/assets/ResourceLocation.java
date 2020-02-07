package inferno.saigo.client.assets;

public class ResourceLocation {
    private String domain = "saigo";
    private String path;

    public ResourceLocation(String domain, String path) {
        this.domain = domain;
        this.path = path;
    }

    public ResourceLocation(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return ("assets/"+domain+"/"+path);
    }
}

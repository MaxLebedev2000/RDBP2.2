package max.lab6.server;

public class Tags {

    private String tags;

    public Tags(String tags) {
        this.tags = tags;
    }


    /////////////////

    public Tags(org.json.JSONObject json){
        this.tags = json.getString("tags");

    }



    public org.json.JSONObject getJson(){
        org.json.JSONObject json = new org.json.JSONObject();
        json.put("tags", this.tags);
        return json;

    }

    @Override
    public String toString() {
        return "tags: " + getTags();

    }

    public String getTags() {
        return tags;
    }




}



package max.lab6.server;



public class Task {
    private String title;
    private String description;
    private String deadline;
    private String tags;
    private String owner;


    public Task(String title, String description, String deadline, String tags, String owner) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
        this.owner = owner;
    }





    ////////////

    public Task(org.json.JSONObject json){
        this.title = json.getString("title");
        this.description = json.getString("description");
        this.deadline =  json.getString("deadline");
        this.tags = json.getString("tags");
        this.owner = json.getString("owner");

    }

    public org.json.JSONObject getJson(){
        org.json.JSONObject json = new org.json.JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("deadline", this.deadline);
        json.put("tags", this.tags);
        json.put("owner", this.owner);
        return json;
    }

    ////////



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public  String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getOwner() {
        return owner;
    }

    @Override
    public String toString(){
        return "title: "+getTitle()+"\n"+"description: "+getDescription()+"\n"+"deadline: "+getDeadline()+"\n"+"tags: "+getTags() + "Владелец: " + getOwner(); }

}


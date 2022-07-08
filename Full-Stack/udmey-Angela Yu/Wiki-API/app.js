const mongoose = require("mongoose");
const express = require("express");
const ejs = require("ejs")
const bodyParser = require("body-parser");

const app = new express();

app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

mongoose.connect("mongodb://localhost:27017/wikiDB", {useNewUrlParser: true});

const articleSchema = {
    title: String, 
    content: String
}

const Article = mongoose.model("Article", articleSchema);

app.get("/articles", function(req, res){
    Article.find(function(err, foundArticle){
        if(!err){
            res.send(foundArticle);
        }else{
            console.log(err);
        }
    });
});


app.listen(3000, function(){
    console.log("listening to " + 3000);
});
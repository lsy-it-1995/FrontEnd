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

// app.get("/articles", function(req, res){
//     Article.find(function(err, foundArticle){
//         if(!err){
//             res.send(foundArticle);
//         }else{
//             console.log(err);
//         }
//     });
// });

// app.post("/articles", function(req, res){
//     const data = new Article({
//         title: req.body.title,
//         content: req.body.content
//     });
//     data.save(function(err){
//         if(!err){
//             res.send("Success");
//         }else{
//             res.send(err);
//         }
//     });

// });

// app.delete("/articles", function(req, res){
//     Article.deleteMany({}, function(err){
//         if(!err){
//             res.send("Delete success");
//         }else{
//             res.send(err);
//         }
//     });
// });
///////////////////////////////////Requests for all articles///////////////////////////////////
app.route("/articles")
    .get(function(req, res){
        Article.find(function(err, foundArticle){
            if(!err){
                res.send(foundArticle);
            }else{
                console.log(err);
            }
        });
    })

    .post(function(req, res){
        const data = new Article({
            title: req.body.title,
            content: req.body.content
        });
        data.save(function(err){
            if(!err){
                res.send("Success");
            }else{
                res.send(err);
            }
        });
    
    })

    .delete(function(req, res){
        Article.deleteMany({}, function(err){
            if(!err){
                res.send("Delete success");
            }else{
                res.send(err);
            }
        });
    });
///////////////////////////////////End Requests for all articles///////////////////////////////////


/////////////////////////////////// Requests for an article///////////////////////////////////

app.route("/articles/:articleTitle")
    .get(function(req, res){
        const query = {title: req.params.articleTitle};
        Article.findOne(query, function(err, foundArticle){
            if (foundArticle) {
                res.send(foundArticle);
            } else {
                res.send("No articles matching that title was found.");
            }
        });
    })
    .put(function(req, res){
        const query = {title: req.params.articleTitle};
        const updateValue = {title: req.body.title, content: req.body.content};
        const option = {overwrite: true};
        Article.replaceOne(query, updateValue, option, function(err,result){
              if(!err){
                res.send("Successfully updated the selected article.");
              }else{
                res.send(err);
              }
            }
          );
    })

    .patch(function(req, res){
        const query = {title: req.params.articleTitle};
        const updateValue = {title: req.body.title, content: req.body.content};
        Article.findOneAndUpdate(query, updateValue, function(err){
            if(!err){
                res.send("success update patch");
            }else{
                res.send(err)
            }
        });
    })
    
    .delete(function(req, res){
        const query = {title: req.params.articleTitle};
        Article.deleteOne(query, function(err){
            if(!err){
                res.send("success update delete");
            }else{
                res.send(err)
            }
        });
    });


app.listen(3000, function(){
    console.log("listening to " + 3000);
});
const mongoose = require('mongoose');
main().catch(err => console.log(err));

async function main(){
  // Connection URL
  const url = 'mongodb://localhost:27017';
  const dbName = "/fruitsDB";
  mongoose.connect(url+dbName, {useNewUrlParser: true});

  const fruitSchema = new mongoose.Schema({
    name: {
      type: String,
      required:[true, 'no name specified']
    },
    rating: {
      type: Number,
      min: 1,
      max: 30
    },
    review: String
  });

  const Fruit = mongoose.model("Fruit", fruitSchema);

  const fruit = new Fruit({
    name: "aa",
    rating: 12,
    review: "Pretty solid as a fruit"
  });

  // fruit.save();

  const peopleSchema = new mongoose.Schema({
    name: String,
    age: Number
  });

  const Person = mongoose.model("Person", peopleSchema);

  const firstPerson = new Person({
    name: "John",
    age: 37
  });

  // firstPerson.save();

  const apple = {
    name: "Apple",
    rating: 23,
    review: "sugar"
  }

  const banana = {
    name: "Banana",
    rating: 5,
    review: "ok"
  }

  const orange = {
    name: "Orange",
    rating: 9,
    review: "juicy"
  }

  // Fruit.insertMany([apple, banana, orange], function(err){
  //   if(err){
  //     console.log(err);
  //   }else{
  //     console.log("Success");
  //   }
  // });

// 
  // Fruit.collection.updateOne({_id:ObjectId("62c04f41bf058a1b61316eea")}, {name:"fk"});
  Fruit.updateOne({ _id: "62c04f41bf058a1b61316eea" }, { name: "test" }, function (err) {
    if (err) {
        console.log(err);
    }
    else {
        console.log("Succesfully updated the document.");
    }
  });
  console.log(Fruit.collection("fruits").find());


  // await mongoose.connection.close();
  // Fruit.find(function(err, fruits){
  //   if(err){
  //     console.log(err);
  //   }else{
  //     fruits.forEach((fruit) =>{
  //       console.log(fruit);
  //       mongoose.connection.close();
  //     });
  //     console.log("well");
  //   }
  // });
}
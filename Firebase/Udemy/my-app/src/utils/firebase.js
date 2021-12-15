import {initializeApp} from 'firebase/app'
import {getFirestore, doc, setDoc, addDoc, collection, getDoc } from 'firebase/firestore';

const firebaseApp = initializeApp({
    apiKey: "AIzaSyB1T55Rsn_TkJgqVZKNyq89Kh9pXLY7MtQ",
    authDomain: "practice-b594f.firebaseapp.com",
    projectId: "practice-b594f",
    storageBucket: "practice-b594f.appspot.com",
    messagingSenderId: "376873921077",
    appId: "1:376873921077:web:d949d59793bbfa987edf7c",
    measurementId: "G-R92FM8RMCS"
});

const firestore = getFirestore();
const cars = doc(firestore, 'cars/IBPAYmBwW2AO5EC9qnps');
async function writeData(){
    const docData = {
        brand:'Toyota',
        price: 100,
        color: 'Black',
        available: false,
    };
    
    setDoc(cars, docData, {merge: true})
        .then(()=>{
            console.log("Has been added");
        })
        .catch((error) => {
            console.log(`Error is ${error}`);
        })
}

const orderCollection = collection(firestore, 'orders');

async function addNewDoc(){
    const newDoc = await addDoc(orderCollection, {
        customer: 'lee',
        drink: 'latte',
        total_cost: '8230948',
    });
    console.log(`Your doc is created ${newDoc.path}`);
}

async function readSingleDoc(){
    const mysnapshot = await getDoc(cars);
    if(mysnapshot.exists()){
        const docData = mysnapshot.data();
        console.log(`Data is ${JSON.stringify(docData)}`);
    }
}

console.log("hello Firestore")
writeData()
addNewDoc();
readSingleDoc();

const redis = require("redis");
const client = redis.createClient();


const { promisify } = require("util");
const setAsync = promisify(client.set).bind(client);


const baseURL = 'https://jobs.github.com/positions.json';
var fetch = require('node-fetch');

async function fetchGithub() {

  console.log('fetching github')

  let resultCount = 1, onPage = 0;
  const allJobs = [];

  // fetch all pages
  while(resultCount > 0) {
      const res = await fetch(`${baseURL}?page=${onPage}`);
      const jobs = await res.json();
      allJobs.push(...jobs);
      resultCount = jobs.length;
      console.log('got', resultCount, 'jobs');
      onPage++;
  }
  console.log('got', allJobs.length, 'jobs total')

  //filter algo
  const jrJobs = allJobs.filter(job=> {
      const jobTitles = job.title.toLowerCase();
      if(
        jobTitles.includes('senior')||
        jobTitles.includes('sr')||
        jobTitles.includes('manager')||
        jobTitles.includes('architect')
      ){return false;}
      return true;
  })

  console.log('filter', jrJobs.length);
  const success = await setAsync('github', JSON.stringify(jrJobs));
  console.log({success});

  
  //set in redis
  const success = await setAsync('github', JSON.stringify(allJobs));
  console.log({success});


}

module.exports = fetchGithub;


//32:00

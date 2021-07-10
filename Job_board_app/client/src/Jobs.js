import React from 'react';
import Typography from '@material-ui/core/Typography';
import Job from './Job';
import MobileStepper from '@material-ui/core/MobileStepper';
import Button from '@material-ui/core/Button';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
import JobModel from './JobModel'

export default function Jobs({jobs}){

  //model
  const [open, setOpen] = React.useState(false);
  const [selectedJob, selectJob] = React.useState({});
  const handleClickOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  //pagination
  const nums_jobs = jobs.length;
  const nums_page = Math.ceil(nums_jobs / 50);
  const [activeStep, setActiveStep] = React.useState(0);
  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };
  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  //step 0 (0-49), step 1(50-99)
  const JobsOnPage =  jobs.slice(activeStep * 50, (activeStep * 50) + 50);

  return(
      <div className='Jobs'>
      <JobModel open={open} job={selectedJob} handleClose={handleClose}/> 

          <Typography variant="h4" component ="h1">
              ENTRY LEVEL SOFTWARE JOBS
          </Typography>

          <Typography variant="h6" component ="h2">
              FOUND {nums_jobs} Jobs
          </Typography>
          {
              JobsOnPage.map(
                  (job, i) => <Job key={i} job={job} onClick={() =>{
                    handleClickOpen()
                    selectJob(job)
                  }}/>
              )
          }
          <div>
              Page {activeStep + 1} of {nums_page}
          </div>
          <MobileStepper
              variant="progress"
              steps={nums_page}
              position="static"
              activeStep={activeStep}
              nextButton={
                <Button size="small" onClick={handleNext} disabled={activeStep === nums_page - 1}>
                Next <KeyboardArrowRight />
                </Button>
              }
              backButton={
                <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
                  Back <KeyboardArrowLeft />
                </Button>
              }
          />
      </div>
  );
}

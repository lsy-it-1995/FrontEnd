import React from 'react'

const AddEmployee = () => {
  return (
    <div class = "flex max-w-2xl shadow mx-auto border-b">
        <div className="px-8 py-8">
            <div className="font-thin text-2xl tracking-wider">
                <h1>Add New Employee</h1>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">FirstName</label>
                <input type="text" className="h-10 w-96 border mt-2"></input>
            </div>
        </div>
    </div>
  )
}

export default AddEmployee
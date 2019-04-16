import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {

  myForm : FormGroup
  status : string = ""
  task : {}
  constructor(private taskService : TaskService) { }

  ngOnInit() {
    this.myForm = new FormGroup({
      'taskGroup': new FormGroup({
          'taskName': new FormControl('', [Validators.required], []),
          'priority': new FormControl('', Validators.required),
          'parentTask': new FormControl('', Validators.required),
          'startDate': new FormControl('', Validators.required),
          'endDate': new FormControl('', Validators.required)
      })
  })

  this.myForm.statusChanges.subscribe((data:any) => console.log(data));
  }

  onSubmit() {
    this.task = {"parentTask" : this.myForm.value.taskGroup.parentTask , 
    "newTask" : {"taskName" : this.myForm.value.taskGroup.taskName,
    "startDate" : this.myForm.value.taskGroup.startDate,
    "endDate" : this.myForm.value.taskGroup.endDate,
    "priority" : this.myForm.value.taskGroup.priority}}
    this.taskService.addTask(JSON.stringify(this.task))
        .then(res => {
            console.log(res);
            if (res.parentId > 0) {
              this.status = "Task Created Successfully!"
              this.myForm.reset();
            }
        }, err => {
            console.log('server err');
            console.log(err);
        })
        .catch(err => {
            console.log('client err');
            console.log(err);
        })
  }

  resetForm() {
    this.myForm.reset();
  }
}

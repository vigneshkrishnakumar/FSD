import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { TaskViewComponent } from './components/task-view/task-view.component';
import { TaskAddComponent } from './components/task-add/task-add.component';
import { TaskPipe } from './pipes/task.pipe';
import { TaskService } from './services/task.service';
import { TaskEditComponent } from './components/task-edit/task-edit.component';

const routes : Routes = [
  { path: '', redirectTo: '/viewTask', pathMatch: 'full' },
  { path: 'viewTask', component: TaskViewComponent},
  { path: 'addTask', component: TaskAddComponent},
  { path: 'editTask/:id', component: TaskEditComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    TaskViewComponent,
    TaskAddComponent,
    TaskPipe,
    TaskEditComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes),
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [TaskPipe,TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ScrumService } from '../service/scrum-service.service';
import { Scrum } from '../model/scrum/scrum';

@Component({
  selector: 'app-scrum-dialog',
  templateUrl: './scrum-dialog.component.html',
  styleUrls: ['./scrum-dialog.component.css']
})
export class ScrumDialogComponent implements OnInit {

  //scrumList: Scrum[];
  dialogTitle: String;
  //title : String;
  form: FormGroup;
  scrum: Scrum;
  //id: Number;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ScrumDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data,
    private scrumService: ScrumService) {

    this.scrum = data.scrum
    this.dialogTitle = data.title

    this.form = fb.group({
        title: [this.scrum.title, Validators.required]
        //id: [this.id, Validators.required]
  });
   }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }

  //需要处理添加和更新
  /**
  save() {
    this.title = this.form.get('title').value;
    if (this.title) {
      this.scrumService.saveNewScrum(this.title).subscribe(

        response => {
          console.log(response)
        }
      )
    }

    this.dialogRef.close();
  }**/

  save() {
    this.mapFormToScrumModel();
    if (!this.scrum.id) {
      this.scrumService.saveNewScrum(this.scrum.title).subscribe();
      //this.scrumService.retrieveAllScrumBoards().subscribe(
        //response => {
          //this.scrumList = response;
        //}
      //);
    }else{
      this.scrumService.updateScrum(this.scrum).subscribe();
    }
    this.dialogRef.close();
  }

  private mapFormToScrumModel(): void {
    this.scrum.title = this.form.get('title').value;
    //this.scrum = this.form.get('id').value;
  }

}

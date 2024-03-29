import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Scrum } from '../model/scrum/scrum';
import { Task } from '../model/task/task';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ScrumService {

  private scrumAppUrl = environment.scrumAppUrl;

  constructor(private http: HttpClient) { }

  retrieveAllScrumBoards(): Observable<Scrum[]> {
    return this.http.get<Scrum[]>(this.scrumAppUrl + '/scrums/');
  }

  retrieveScrumById(id: String): Observable<Scrum> {
    return this.http.get<Scrum>(this.scrumAppUrl + '/scrums/' + id);
  }

  getScrumById(id: String): Observable<Scrum> {
    return this.http.get<Scrum>(this.scrumAppUrl + '/scrums/' + id);
  }

  saveNewScrum(title: String): Observable<string> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
    let options = { headers: headers };
    let jsonObject = this.prepareTiTleJsonObject(title);
    return this.http.post<string>(
      this.scrumAppUrl + '/scrums/',
      jsonObject,
      options
    );
  }

  saveNewTaskInScrum(scrumId: String, task: Task): Observable<Task> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
    let options = { headers: headers };
    return this.http.post<Task>(
      this.scrumAppUrl + '/scrums/' + scrumId + '/tasks/',
      task,
      options);
  }

  private prepareTiTleJsonObject(title: String) {
    const object = {
      title: title
    }
    return JSON.stringify(object);
  }

  /**
  deleteScrum(scrum: Scrum): Observable<Scrum> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
    let options = { headers: headers };
    return this.http.delete<Scrum>(
      this.scrumAppUrl + '/scrums/' + scrum.id
    );
  }
   **/

  updateScrum(scrum: Scrum): Observable<Scrum> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
    let options = { headers: headers };
    return this.http.put<Scrum>(
      this.scrumAppUrl + '/scrums/' + scrum.id,
      scrum,
      options);
  }
}

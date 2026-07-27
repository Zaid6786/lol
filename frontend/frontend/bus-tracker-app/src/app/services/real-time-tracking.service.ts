import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class RealTimeTrackingService {

    private apiUrl = `${environment.apiUrl}/realtimetracking`;

    constructor(private http: HttpClient) { }

    getLatestTracking(busNo: string): Observable<any> {
        return this.http.get(`${this.apiUrl}/bus/${busNo}`);
    }
}
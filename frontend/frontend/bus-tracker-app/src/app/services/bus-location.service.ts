import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { BusLocation } from 'src/app/models/bus-location';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class BusLocationService {

    private apiUrl = `${environment.apiUrl}/buslocation`;

    constructor(private http: HttpClient) { }

    getAllLocations(): Observable<BusLocation[]> {
        return this.http.get<BusLocation[]>(`${this.apiUrl}/getall`);
    }

    getLocationById(id: number): Observable<BusLocation> {
        return this.http.get<BusLocation>(`${this.apiUrl}/get/${id}`);
    }

}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShopOwnerService {
  private baseUrl = 'http://localhost:8081/shopOwners'; // ✅ matches your Spring Boot endpoint

  constructor(private http: HttpClient) {}

  /** Register new Shop Owner */
  registerShopOwners(shopOwner: any): Observable<any> {
    return this.http.post(this.baseUrl, shopOwner, { responseType: 'text' });
  }

  /** Get all Shop Owners */
  getShopOwners(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  /** Delete Shop Owner by ID */
  deleteShopOwners(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /** Update Shop Owner */
  updateShopOwners(shopOwner: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${shopOwner.id}`, shopOwner, { responseType: 'text' });
  }
}

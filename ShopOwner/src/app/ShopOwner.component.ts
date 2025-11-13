import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ShopOwnerService } from './ShopOwner.service';

@Component({
  selector: 'app-shop-owner',
  templateUrl: './ShopOwner.component.html',
  styleUrls: ['./ShopOwner.component.scss']
})
export class ShopOwnerComponent implements OnInit {
  shopOwners: any[] = [];
  shopOwnerToUpdate: any = {
    id: null,
    name: '',
    shopName: '',
    contactInfo: '',
    email: ''
  };

  constructor(private shopOwnerService: ShopOwnerService) {}

  ngOnInit(): void {
    this.loadShopOwners();
  }

  loadShopOwners(): void {
    this.shopOwnerService.getShopOwners().subscribe(
      (data: any) => {
        this.shopOwners = data;
      },
      (error) => {
        console.error('Error loading shop owners:', error);
      }
    );
  }

  register(form: NgForm): void {
    if (form.valid) {
      const shopOwner = {
        name: form.value.name,
        shopName: form.value.shopName,
        contactInfo: form.value.contactInfo,
        email: form.value.email || ''
      };

      this.shopOwnerService.registerShopOwners(shopOwner).subscribe(
        (response) => {
          console.log('Registration successful:', response);
          form.reset();
          this.shopOwnerToUpdate = {
            id: null,
            name: '',
            shopName: '',
            contactInfo: '',
            email: ''
          };
          this.loadShopOwners();
        },
        (error) => {
          console.error('Registration error:', error);
          alert(error.error || 'Registration failed');
        }
      );
    }
  }

  edit(shopOwner: any): void {
    this.shopOwnerToUpdate = { ...shopOwner };
  }

  updateShopOwner(): void {
    if (this.shopOwnerToUpdate.id) {
      this.shopOwnerService.updateShopOwners(this.shopOwnerToUpdate).subscribe(
        (response) => {
          console.log('Update successful:', response);
          this.loadShopOwners();
          // Close modal if using Bootstrap
          const modalElement = document.getElementById('editModal');
          if (modalElement) {
            const modal = (window as any).bootstrap?.Modal?.getInstance(modalElement);
            if (modal) {
              modal.hide();
            }
          }
        },
        (error) => {
          console.error('Update error:', error);
          alert(error.error || 'Update failed');
        }
      );
    }
  }

  deleteShopOwner(shopOwner: any): void {
    if (confirm('Are you sure you want to delete this shop owner?')) {
      this.shopOwnerService.deleteShopOwners(shopOwner.id).subscribe(
        (response) => {
          console.log('Delete successful:', response);
          this.loadShopOwners();
        },
        (error) => {
          console.error('Delete error:', error);
          alert(error.error || 'Delete failed');
        }
      );
    }
  }
}

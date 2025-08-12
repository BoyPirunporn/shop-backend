package com.backend.shop.applications.dto;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface Views {
    interface ResponseWithPayload {
    }

    interface DataTable extends DataTablesOutput.View, ResponseWithPayload {
    }

    interface RolePermission extends DataTablesOutput.View, ResponseWithPayload {
    } // สำหรับ route 1

    interface MenuItem extends DataTablesOutput.View, ResponseWithPayload {
        interface Full extends DataTablesOutput.View, ResponseWithPayload {
        }

        interface Role extends DataTablesOutput.View, ResponseWithPayload {
        }

        interface Permission extends DataTablesOutput.View, ResponseWithPayload {
        }
    } // สำหรับ route 2

    interface MenuItemWithOutRoleMenuPermission extends ResponseWithPayload {
        interface Full extends DataTablesOutput.View, ResponseWithPayload {
        }

        interface Role extends DataTablesOutput.View, ResponseWithPayload {
        }

        interface Permission extends DataTablesOutput.View, ResponseWithPayload {
        }
    } // สำหรับ route 2

    interface MenuItemDatatable extends DataTablesOutput.View, ResponseWithPayload {
        interface Full extends DataTablesOutput.View, ResponseWithPayload {
        }

        interface Role extends DataTablesOutput.View, ResponseWithPayload {
        }

        interface Permission extends DataTablesOutput.View, ResponseWithPayload {
        }
    } // สำหรับ route 2
}

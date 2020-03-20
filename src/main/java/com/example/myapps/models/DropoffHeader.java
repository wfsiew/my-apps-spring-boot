package com.example.myapps.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "cx_dropoff_header")
public class DropoffHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "dropoff_no")
    private String dropoffNo;

    @Column(name = "dropoff_branch_id") 
    private String dropoffBranchId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "edit_date")
    private LocalDateTime editDate;

    @Column(name = "edit_by")
    private String editBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "payment_type")
    private String paymentType;

    public DropoffHeader() {
        
    }

    /**
     * @return UUID return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return String return the dropoffNo
     */
    public String getDropoffNo() {
        return dropoffNo;
    }

    /**
     * @param dropoffNo the dropoffNo to set
     */
    public void setDropoffNo(String dropoffNo) {
        this.dropoffNo = dropoffNo;
    }

    /**
     * @return String return the dropoffBranchId
     */
    public String getDropoffBranchId() {
        return dropoffBranchId;
    }

    /**
     * @param dropoffBranchId the dropoffBranchId to set
     */
    public void setDropoffBranchId(String dropoffBranchId) {
        this.dropoffBranchId = dropoffBranchId;
    }

    /**
     * @return String return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return String return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @return Time return the editDate
     */
    public LocalDateTime getEditDate() {
        return editDate;
    }

    /**
     * @param editDate the editDate to set
     */
    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    /**
     * @return String return the editBy
     */
    public String getEditBy() {
        return editBy;
    }

    /**
     * @param editBy the editBy to set
     */
    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    /**
     * @return String return the createBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy the createBy to set
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return LocalDateTime return the createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
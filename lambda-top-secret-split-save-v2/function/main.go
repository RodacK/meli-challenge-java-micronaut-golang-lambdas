package main

import (
	"context"
	"fmt"
	"github.com/aws/aws-lambda-go/lambda"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/dynamodb"
	"github.com/aws/aws-sdk-go/service/dynamodb/dynamodbattribute"
	"errors"
)
type Satellite struct {
	Name string `json:"name"`
	Distance string `json:"distance"`
	Message []string `json:"message"`
}

func HandleRequest(ctx context.Context, satellite Satellite) (string, error) {
	sess := session.Must(session.NewSessionWithOptions(session.Options{
		SharedConfigState: session.SharedConfigEnable,
	}))

	svc := dynamodb.New(sess)

	av, err := dynamodbattribute.MarshalMap(satellite)
	if err != nil {
		return "",errors.New("500-108-Got error marshalling new satellite item: "+err.Error())
	}

	tableName := "satellites-info"

	input := &dynamodb.PutItemInput{
		Item:      av,
		TableName: aws.String(tableName),
	}

	_, err = svc.PutItem(input)
	if err != nil {
		return "",errors.New("500-101-Got error calling PutItem: "+err.Error())
	}
	return fmt.Sprintf("201-103-Successfully added '" + satellite.Name + " to table " + tableName),nil
}

func main() {
	lambda.Start(HandleRequest)
}
// DatabaseBroker class

#include "DatabaseBroker.h"
using namespace std;

DatabaseBroker::DatabaseBroker()
{
   //connect to database
}

DatabaseBroker::~DatabaseBroker()
{
   //disconnect from database

}

DatabaseBroker* DatabaseBroker::getInstance()
{
   static DatabaseBroker *instance;
   return instance;
}

int DatabaseBroker::getPhysician()
{
   return 3;
}

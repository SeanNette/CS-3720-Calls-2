// Database Broker header

#ifndef DATABASEBROKER_H
#define PHYSICIAN_H

using namespace std;

class DatabaseBroker
{
  public:
   static DatabaseBroker* getInstance();

   int getPhysician();

  private:
   DatabaseBroker();
   ~DatabaseBroker();
};

#endif

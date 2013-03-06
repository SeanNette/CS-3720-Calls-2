#include "DatabaseBroker.h"
#include <iostream>

using namespace std;

int main()
{
   //DatabaseBroker::getInstance();
   DatabaseBroker::getPhysician();
   cout << DatabaseBroker::getInstance()->getPhysician() << endl;
   return 0;
}
